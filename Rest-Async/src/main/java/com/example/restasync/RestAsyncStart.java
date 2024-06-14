package com.example.restasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RestAsyncStart {
    public static void main(String[] args){
        SpringApplication.run(RestAsyncStart.class);

    }
}
