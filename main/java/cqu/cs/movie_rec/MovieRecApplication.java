package cqu.cs.movie_rec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
public class MovieRecApplication {
    public static void main(String[] args) {

        SpringApplication.run(MovieRecApplication.class, args);
        System.out.println("hello");
    }
}