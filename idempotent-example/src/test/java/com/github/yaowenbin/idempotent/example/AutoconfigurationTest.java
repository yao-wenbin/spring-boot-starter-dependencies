package com.github.yaowenbin.idempotent.example;

import com.github.yaowenbin.idempotent.IdempotentAspect;
import com.github.yaowenbin.idempotent.example.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
class AutoconfigurationTest extends IdempotentExampleTest {

    @Autowired
    IdempotentStore cache;

    @Autowired
    IdempotentAspect aspect;

    @Test
    void autoconfiguration() {
        assertNotNull(cache);
        assertNotNull(aspect);
    }



}
