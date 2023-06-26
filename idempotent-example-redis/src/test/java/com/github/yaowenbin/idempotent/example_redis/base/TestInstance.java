package com.github.yaowenbin.idempotent.example_redis.base;

import com.github.yaowenbin.idempotent.Idempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Slf4j
@Component
public class TestInstance {

    @Idempotent
    public void doSomething() {
        log.info("do something");
    }

}
