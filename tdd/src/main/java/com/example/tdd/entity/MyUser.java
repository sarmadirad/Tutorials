package com.example.tdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MyUser {
    @Id
    private Long id;
    private String username;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MyUser(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public MyUser() {

    }


    public Long getId() {
        return id;
    }
}
