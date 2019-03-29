package cqu.cs.movie_rec.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import scala.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collection = "movies")
//@Entity(name = "movies" )
public class Movie implements Serializable {

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
    private Long movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genres;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Movie(String title, String genres) {
        this.title = title;
        this.genres = genres;
    }

    public Movie() {
    }
}