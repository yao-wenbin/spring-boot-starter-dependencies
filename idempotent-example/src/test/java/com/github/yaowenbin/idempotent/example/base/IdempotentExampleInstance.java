package com.github.yaowenbin.idempotent.example.base;

import com.github.yaowenbin.idempotent.Idempotent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IdempotentExampleInstance {

    @Idempotent
    public void doSomething() {
        log.info("do something");
    }

    @Idempotent(key = "#user.getUsername()")
    public void user(User user) {
        log.info("do user");
    }

    @Idempotent(key = "token")
    public void token() {
        log.info("do token");
    }


    @Idempotent(key = "#TOKEN")
    public void token2() {
        log.info("do token2");
    }

}

