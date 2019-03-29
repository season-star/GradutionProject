package cqu.cs.movie_rec.dao;

import cqu.cs.movie_rec.domain.Rec;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecDao extends MongoRepository<Rec, String> {
    List<Rec> findByUserId(Long userId);
}
