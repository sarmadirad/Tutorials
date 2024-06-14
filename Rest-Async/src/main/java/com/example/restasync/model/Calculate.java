package com.example.restasync.model;


import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class Calculate {

    private int p1;
    private int p2;
    private String add;
    private String subtraction;
    private String multiplication;
    private String division;
}
