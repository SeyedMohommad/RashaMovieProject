package com.example.rasha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class RashaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RashaApplication.class, args);
    }

}
