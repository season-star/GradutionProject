package cqu.cs.movie_rec.controller;

import cqu.cs.movie_rec.domain.Movie;
import cqu.cs.movie_rec.domain.Rec;
import cqu.cs.movie_rec.service.MovieService;
import cqu.cs.movie_rec.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    RecService recService;
    @Autowired
    MovieService movieService;

    @GetMapping("/search")
    public String search(@RequestParam(value="userId",required = false,defaultValue = "1")Long userId, Model model) {
        String s = "";
        int i = 1;
        System.out.println("into this");
        for (Rec rec:recService.findByUserId(userId)) {
            for (Movie movie:movieService.findByMovieId(rec.getMovieId())) {
                s = s.concat("第"+ i + "部是</b>");
//                s = s.concat(movie.getMovieId());
                s = s.concat(movie.getTitle());
                s = s.concat(movie.getGenres());
                s = s.concat("  *  "+"</b>");
                i = i + 1;
            }
        }
        model.addAttribute("message", "为用户"+ userId + "推荐的电影: \n" + s);
        return "search";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value="userId",required = false,defaultValue = "1")Long userId, Model model){
        String s = "";
        int i = 1;
        for (Rec rec:recService.findByUserId(userId)) {
            for (Movie movie:movieService.findByMovieId(rec.getMovieId())) {
                s = s.concat("第"+ i + "电影部");
                s = s.concat(" ID是"+ movie.getMovieId().toString()+"，");
                s = s.concat(" 名称是"+ movie.getTitle()+"，");
                s = s.concat(" 类型是"+ movie.getGenres());
                s = s.concat("。  "+"\n");
                i = i + 1;
            }
        }
        model.addAttribute("message", "用户"+ userId + "推荐的电影: " + s);
        return  "index";
    }

//    @RestController
//    public class HelloWorldController {
//        @RequestMapping("/hello")
//        public String index() {
//            return "Hello World";
//        }
//    }
}