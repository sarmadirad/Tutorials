package com.example.restasync.service;

import com.example.restasync.config.AsyncConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private Logger logger = LoggerFactory.getLogger(AsyncConfig.class);
    @Async
    public CompletableFuture<String> Division(int p1, int p2){
        logger.info("Operation name : Division"  );
        String result = String.valueOf(p1/p2);

        return  CompletableFuture.completedFuture(result);
    }
    @Async
    public CompletableFuture<String> Add(int p1, int p2){
        logger.info("Operation name : Add"  );
        String result = String.valueOf(p1+p2);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  CompletableFuture.completedFuture(result);
    }
    @Async
    public CompletableFuture<String> multiplication(int p1, int p2){
        logger.info("Operation name : multiplication"  );
        String result = String.valueOf(p1*p2);

        return  CompletableFuture.completedFuture(result);
    }
    @Async
    public CompletableFuture<String> subtraction(int p1, int p2){
        logger.info("Operation name : subtraction"  );
        String result = String.valueOf(p1-p2);

        return  CompletableFuture.completedFuture(result);
    }


}
