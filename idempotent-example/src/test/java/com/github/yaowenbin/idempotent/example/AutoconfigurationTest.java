package com.github.yaowenbin.idempotent.example;

import com.github.yaowenbin.idempotent.example.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.IdempotentCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
public class AutoconfigurationTest extends IdempotentExampleTest {

    @Autowired
    IdempotentCache cache;

    @Test
    void loadCache() {
        assertNotNull(cache);
    }


}
