package com.github.yaowenbin.idempotent.example_redis;

import com.github.yaowenbin.idempotent.example_redis.base.IdempotentExampleTest;
import com.github.yaowenbin.idempotent.example_redis.base.TestInstance;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
public class IdempotentTest extends IdempotentExampleTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IdempotentStore store;

    @Autowired
    TestInstance testInstance;

    @Test
    void idempotent_callMethodShouldStoreInRedis() throws NoSuchMethodException {
        testInstance.doSomething();

        Method method = TestInstance.class.getMethod("doSomething");
        String key = store.buildKey(method, "");
        assertThat(redisTemplate.hasKey(key)).isTrue();
    }

}
