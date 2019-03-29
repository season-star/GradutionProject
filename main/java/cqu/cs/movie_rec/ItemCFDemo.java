package cqu.cs.movie_rec;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ItemCFDemo {

    final static int NEIGHBORHOOD_NUM = 2;
    final static int RECOMMENDER_NUM = 3;

    public static void main(String[] args) throws IOException,TasteException
    {
        URL url=ItemCFDemo.class.getClassLoader().getResource("ratings.csv");
        DataModel model = new FileDataModel(new File(url.getFile()));
        ItemSimilarity item = new EuclideanDistanceSimilarity(model);
        Recommender r = new GenericItemBasedRecommender(model,item);
        LongPrimitiveIterator iter = model.getUserIDs();


        while(iter.hasNext())
        {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid,RECOMMENDER_NUM);  //获取推荐结果
//            System.out.printf("用户:%s",uid);
//            System.out.print("|| 推荐商品：");
            //遍历推荐结果
            for(RecommendedItem ritem : list)
            {
//                System.out.printf(ritem.getItemID()+" ");
            }
//            System.out.println();
        }
    }
}