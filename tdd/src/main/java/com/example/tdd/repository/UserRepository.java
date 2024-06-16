package com.example.tdd.repository;

import com.example.tdd.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {
    MyUser findByUsername(String username);
}
