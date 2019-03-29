package cqu.cs.movie_rec.dao;

import cqu.cs.movie_rec.domain.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao extends MongoRepository<Movie, String> {
    List<Movie> findByMovieId(Long movieId);
}
