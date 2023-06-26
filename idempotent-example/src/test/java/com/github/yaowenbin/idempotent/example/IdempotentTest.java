package com.github.yaowenbin.idempotent.example;

import com.github.yaowenbin.idempotent.IdempotentProperties;
import com.github.yaowenbin.idempotent.example.base.IdempotentExampleInstance;
import com.github.yaowenbin.idempotent.example.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import com.github.yaowenbin.idempotent.IdempotentException;
import com.github.yaowenbin.idempotent.example.base.User;
import com.github.yaowenbin.idempotent.example.base.UserContext;
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
    IdempotentStore cache;

    @Autowired
    UserContext userContext;

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

    @Test
    void idempotent_shouldParseSpelExpression() throws NoSuchMethodException {
        User user = new User();
        user.setUsername("yaowenbin");
        instance.user(user);
    }

    @Test
    void idempotent_shouldParseTokenExpression_whenUserContextImplInstanceMethod() {
        properties.setTokenExpression("@userContextImpl.getToken");
        instance.token();
    }


    @Autowired
    IdempotentProperties properties;

    @Test
    void idempotent_shouldParseTokenExpression_whenUserContextStaticMethod() {
        properties.setTokenExpression("@userContext.getToken");
        instance.token2();
    }


}



