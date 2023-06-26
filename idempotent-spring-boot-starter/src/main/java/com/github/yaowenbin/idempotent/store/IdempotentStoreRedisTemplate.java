package com.github.yaowenbin.idempotent.store;

import com.github.yaowenbin.idempotent.Idempotent;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/25
 */
public class IdempotentStoreRedisTemplate implements IdempotentStore {

    private final RedisTemplate<String, Boolean> redisTemplate;

    private final ValueOperations<String, Boolean> ops;

    private static final String PREFIX = "IDEMPOTENT:";

    public IdempotentStoreRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.ops = redisTemplate.opsForValue();
    }

    @Override
    public boolean exists(String key) {
        return value.equals(
                redisTemplate.hasKey(
                    key
        ));
    }

    @Override
    public IdempotentStore put(String key) {
        ops.setIfAbsent(key, value);
        return this;
    }

    @Override
    public IdempotentStore put(String key, int interval, TimeUnit timeUnit) {
        ops.setIfAbsent(key, value, interval, timeUnit);
        return this;
    }

    @Override
    public void clear() {
        Set<String> keys = redisTemplate.keys(PREFIX.concat("*"));
        if (keys == null || keys.isEmpty()) {
            return ;
        }
        redisTemplate.delete(keys);
    }

    @Override
    public String buildKey(Method method, String simpleKey) {
        String buildKey = IdempotentStore.super.buildKey(method, simpleKey);
        return wrapWithPrefix(buildKey);
    }

    private String wrapWithPrefix(String key) {
        return PREFIX.concat(key);
    }
}
