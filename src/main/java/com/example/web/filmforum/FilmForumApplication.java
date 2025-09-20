package com.example.web.filmforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FilmForumApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FilmForumApplication.class);

        Map<String, Object> props = new HashMap<>();
        props.put("spring.config.name", "application");

        app.setDefaultProperties(props);
        app.run(args);
    }

}
