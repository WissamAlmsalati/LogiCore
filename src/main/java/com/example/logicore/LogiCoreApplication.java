package com.example.logicore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class LogiCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogiCoreApplication.class, args);
    }

}
