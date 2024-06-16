package com.example.tdd.service;

import com.example.tdd.entity.MyUser;
import com.example.tdd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public MyUser getUser(String username){
        return userRepository.findByUsername(username);
    }
}
