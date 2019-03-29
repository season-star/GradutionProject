package cqu.cs.movie_rec.service;

import cqu.cs.movie_rec.dao.RatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingDao ratingDao;
}
