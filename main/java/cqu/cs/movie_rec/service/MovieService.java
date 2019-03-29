package cqu.cs.movie_rec.service;

import cqu.cs.movie_rec.dao.MovieDao;
import cqu.cs.movie_rec.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    public List<Movie> findByMovieId(Long movieId) {
        List<Movie> movies = movieDao.findByMovieId(movieId);
        return movies;
    }
}
