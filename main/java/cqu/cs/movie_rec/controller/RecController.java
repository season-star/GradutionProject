package cqu.cs.movie_rec.controller;

import cqu.cs.movie_rec.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecController {
    @Autowired
    RecService recService;

    @RequestMapping("/get")
    public void getRec(){
        recService.recommend();
    }
}