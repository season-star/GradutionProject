package cqu.cs.movie_rec.dao;

import cqu.cs.movie_rec.domain.Rating;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingDao extends MongoRepository<Rating, String> {
}
