package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceAop {
    public String Division(int p1,int p2){
        return  String.valueOf(p1/p2);
    }
}
