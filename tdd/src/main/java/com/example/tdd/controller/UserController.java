package com.example.tdd.controller;

import com.example.tdd.entity.MyUser;
import com.example.tdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    public ResponseEntity<MyUser> getUser(@RequestParam(value = "userName") String userName){
        return new ResponseEntity<>(userService.getUser(userName), HttpStatus.OK);
    }
}
