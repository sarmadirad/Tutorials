package com.example.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.websocket"})


public class Starter {
    public static void main(String[] args)
    {
        ConfigurableApplicationContext ctx = SpringApplication.run(Starter.class,args);
        ctx.getBean(Starter.class);
        ctx.registerShutdownHook();
    }
}
