package com.github.yaowenbin.idempotent.example.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Component
public class UserContext {

    @Autowired
    UserContextImpl contextImpl;

    public static UserContextImpl USER_CONTEXT;

    @PostConstruct
    public void setup() {
        USER_CONTEXT = contextImpl;
    }

    /**
     * UseCase : using such spring security framework. you can use static method to getUserToken.
     */
    public static String getToken() {
        return USER_CONTEXT.getToken();
    }

}
