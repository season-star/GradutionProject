package cqu.cs.movie_rec.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import scala.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity(name = "ratings")
@Document(collection = "ratings")
public class Rating implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private String id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private Double rating;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Rating() {
    }

    public Rating(Long userId, Long movieId, Double rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }
}
