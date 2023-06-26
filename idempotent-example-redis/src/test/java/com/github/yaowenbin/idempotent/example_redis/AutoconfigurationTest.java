package com.github.yaowenbin.idempotent.example_redis;

import com.github.yaowenbin.idempotent.IdempotentAspect;
import com.github.yaowenbin.idempotent.example_redis.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import com.github.yaowenbin.idempotent.store.IdempotentStoreRedisTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
public class AutoconfigurationTest extends IdempotentExampleTest {


    @Autowired
    IdempotentStore cache;

    @Autowired
    IdempotentAspect aspect;

    @Test
    void loadRedisStore_whenIOCHasRedisTemplate() {
        assertThat(cache).isInstanceOf(IdempotentStoreRedisTemplate.class);
        assertThat(aspect).isNotNull();
    }

}
