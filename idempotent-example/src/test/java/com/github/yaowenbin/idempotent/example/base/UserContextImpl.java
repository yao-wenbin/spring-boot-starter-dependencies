package com.github.yaowenbin.idempotent.example.base;

import org.springframework.stereotype.Component;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Component
public class UserContextImpl {

    /**
     * UseCase : using instance method to get Token.
     */
    public String getToken() {
        return "1234567890";
    }

}
