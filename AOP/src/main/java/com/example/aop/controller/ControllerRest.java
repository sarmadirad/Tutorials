package com.example.aop.controller;

import com.example.aop.service.ServiceAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {
    @Autowired
    ServiceAop serviceAop;

    @GetMapping("/aop")
    public String Devision(@RequestParam(value="p1" ,required = true,defaultValue = "1") int p1 ,@RequestParam(value = "p2",required = true,defaultValue = "0") int p2){
        return serviceAop.Division(p1,p2);
    }
}
