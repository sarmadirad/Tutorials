package com.example.tdd.repository;

import com.example.tdd.entity.MyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
public class UserRepositoryTest {
    // use H2 memory database for repository
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    public void setup(){
        MyUser user = new MyUser();
        user.setId(10L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        //testEntityManager.persist(user);
        userRepository.save(user);
    }
    @Test
    public void whenFindByUsername_thenReturnUser(){
        // given
        Long id=10L;
        // String userName = "testuser" ;   //result test ok;
        String userName = "testuser1" ;  //result test fail;
      // when
      //  MyUser found =testEntityManager.find(MyUser.class,id);
        MyUser found = userRepository.findByUsername(userName);
        Assertions.assertEquals(found.getUsername(),userName);
    }
    @Test
    public void whenFindByNonExistingUsername_thenReturnNull() {
    // given
        String nonexistinguser = "nonexistinguser";

        // when
        MyUser found = userRepository.findByUsername(nonexistinguser);


        // then
        // JUnit
        Assertions.assertNull(found);
    }
}
