package com.github.yaowenbin.idempotent.example.base;

import com.github.yaowenbin.idempotent.Idempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IdempotentExampleInstance {

    @Idempotent
    public void doSomething() {
        log.info("do something");
    }

}