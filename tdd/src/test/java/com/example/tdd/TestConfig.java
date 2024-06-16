package com.example.tdd;

import com.example.tdd.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    //define for custom config test
    @Bean
    public UserService getUserService(){
        return new UserService();
    }
}
