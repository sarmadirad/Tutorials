package com.example.tdd.controller;

import com.example.tdd.entity.MyUser;
import com.example.tdd.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setup(){
        MyUser user = new MyUser();
        user.setId(10L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        Mockito.when(userService.getUser(user.getUsername()))
                .thenReturn(user);

    }
    @Test
    public void whenFindByUsername_thenReturnUser(){
         String userName = "testuser" ;   //result test ok;
        //String userName = "testuser1" ;  //result test fail;

        try {
            MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
            paramsMap.add("userName", userName);
            mvc.perform(get("/user").params(paramsMap))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.username").value(userName))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
