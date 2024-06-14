package com.example.rabbitmq.controller;




import com.example.rabbitmq.producer.RabbitMQProducer;
import com.example.rabbitmq.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @GetMapping("/publish")

    public ResponseEntity<String> sendMessage(@RequestBody Users user){


        rabbitMQProducer.sendMessage(user);
     return ResponseEntity.ok("OK");

    }

}
