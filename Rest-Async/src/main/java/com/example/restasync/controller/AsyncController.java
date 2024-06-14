package com.example.restasync.controller;

import com.example.restasync.config.AsyncConfig;
import com.example.restasync.model.Calculate;
import com.example.restasync.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController

public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(AsyncController.class);
    @Autowired
    AsyncService asyncService;
    @GetMapping("/async")
    public ResponseEntity<Calculate> Calculate(@RequestParam(value="p1" ,required = true,defaultValue = "1") int p1 , @RequestParam(value = "p2",required = true,defaultValue = "0") int p2) throws ExecutionException, InterruptedException {
        CompletableFuture<String> add = asyncService.Add(p1,p2);
        CompletableFuture<String> subtraction = asyncService.subtraction(p1,p2);
        CompletableFuture<String> multiplication = asyncService.multiplication(p1,p2);
        CompletableFuture<String> division = asyncService.Division(p1,p2);
        // Wait until they are all done
        CompletableFuture.allOf(add,subtraction,multiplication,division).join();
        Calculate calculate = new Calculate();
        calculate.setP1(p1);
        calculate.setP2(p2);
        calculate.setAdd(add.get());
        calculate.setSubtraction(subtraction.get());
        calculate.setMultiplication(multiplication.get());
        calculate.setDivision(division.get());

        logger.info("add : "+ add.get());
        logger.info("subtraction : "+ subtraction.get());
        logger.info("multiplication : "+ multiplication.get());
        logger.info("division : "+ division.get());
        return new ResponseEntity<Calculate>(calculate, HttpStatus.OK);
    }
}
