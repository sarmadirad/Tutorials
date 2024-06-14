package com.example.aop;

import com.example.aop.service.ServiceAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AOPStart {

    public static void main(String[] args){
        SpringApplication.run(AOPStart.class);

    }
}
