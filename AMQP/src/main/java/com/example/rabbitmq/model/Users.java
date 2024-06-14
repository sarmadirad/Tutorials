package com.example.rabbitmq.model;

import java.io.Serializable;

public class Users implements Serializable {
    private static final long serialVersionUID = -2218949334640330008L;
    private final int id;
    private  String name;

    public void setName(String name){
        this.name=name;
    }



    public Users(int id,String name){
        this.id = id;
        this.name=name;
    }
    public int getId(){return id; }
    public String getName(){return name;}


}
