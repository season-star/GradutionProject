package cqu.cs.movie_rec.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import scala.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity(name = "recs")
@Document(collection = "Rec")
//@Table(name = "Rec")
public class Rec implements Serializable {

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long movieId;

    public Rec() {
    }

    public Rec(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

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

}
