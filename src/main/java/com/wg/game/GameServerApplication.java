package com.wg.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class GameServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GameServerApplication.class, args);
    }

}