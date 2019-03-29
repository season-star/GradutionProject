package cqu.cs.movie_rec.service;

import cqu.cs.movie_rec.dao.RatingDao;
import cqu.cs.movie_rec.dao.RecDao;
import cqu.cs.movie_rec.domain.Rec;
import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class RecService {
    @Autowired
    private RecDao recDao;
    @Autowired
    private RatingDao ratingDao;

    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DATA_BASENAME = "movie";
    private static final String TABLE_NAME = "Rec";
    final static int NEIGHBORHOOD_NUM = 3;   //用户邻居数量
    final static int RECOMMENDER_NUM = 3;    //推荐结果个数

    public void recommend() {
        DataModel model = null;
        try {
            model = new MongoDBDataModel(HOST, PORT, DATA_BASENAME, TABLE_NAME, true, true, null);
            UserSimilarity user = new EuclideanDistanceSimilarity(model);
            NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
            Recommender r = new GenericUserBasedRecommender(model, neighbor, user);
//            Recommender r = new CachingRecommender(new SlopeOneRecommender(model));//构造推荐引擎
            //得到所有用户的id集合
            LongPrimitiveIterator iter = model.getUserIDs();
            while (iter.hasNext()) {
                long uid = iter.nextLong();
                List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);  //获取推荐结果
                for (RecommendedItem ritem : list) {
                    //保存推荐结果：uid,ritem.getItemID()
                    recDao.save(new Rec(uid, ritem.getItemID()));
                    System.out.println("保存推荐结果："+ uid + ritem.getItemID());
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (TasteException e) {
            e.printStackTrace();
        }
    }

    public List<Rec> findByUserId(Long userId) {
        List<Rec> recs = recDao.findByUserId(userId);
        return recs;
    }
}