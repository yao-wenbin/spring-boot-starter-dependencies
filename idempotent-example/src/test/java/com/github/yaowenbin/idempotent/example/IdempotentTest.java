package com.github.yaowenbin.idempotent.example;

import com.github.yaowenbin.idempotent.example.base.IdempotentExampleInstance;
import com.github.yaowenbin.idempotent.example.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.IdempotentCache;
import com.github.yaowenbin.idempotent.IdempotentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
class IdempotentTest extends IdempotentExampleTest {

    @Autowired
    IdempotentExampleInstance instance;

    @Autowired
    IdempotentCache cache;

    @AfterEach
    public void afterEach() {
        cache.clear();
    }

    @Test
    void idempotent_shouldThrowIdempotentExceptionAfterSecondCall() {
        instance.doSomething();

        Assertions.assertThrows(IdempotentException.class, () -> {
            instance.doSomething();
        });
    }

    @Test
    void idempotent_shouldProceedAfterFiveSecondThenCall() throws InterruptedException {
        instance.doSomething();
        Thread.sleep(1000);

        Assertions.assertDoesNotThrow(() -> {
            instance.doSomething();
        });
    }

}



