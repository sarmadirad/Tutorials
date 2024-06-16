package com.example.tdd.repository;

import com.example.tdd.TestConfig;
import com.example.tdd.entity.MyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryMockTest {
        // without  database  and mock repository
    @MockBean
   UserRepository userRepository;
    @BeforeEach
    public void setup(){
        MyUser user = new MyUser();
        user.setId(10L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);
    }

    @Test
     public   void whenFindByUsername_thenReturnUser(){
        // String userName = "testuser" ;   //result test ok;
        String userName = "testuser1" ;  //result test fail;
        MyUser found = userRepository.findByUsername(userName);
        Assertions.assertEquals(found.getUsername(),userName);
    }

}
