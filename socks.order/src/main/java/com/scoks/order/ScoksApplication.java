package com.scoks.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScoksApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScoksApplication.class, args);
    }
}
