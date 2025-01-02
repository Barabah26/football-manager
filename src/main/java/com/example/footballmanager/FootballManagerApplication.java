package com.example.footballmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballManagerApplication.class, args);
        System.out.println("http://localhost:9000/swagger-ui/index.html");
        System.out.println("http://localhost:9000/h2-console");
    }

}
