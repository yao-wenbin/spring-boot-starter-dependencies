package com.github.yaowenbin.idempotent.redis;

import com.github.yaowenbin.idempotent.IdempotentCache;

import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/25
 */
public class IdempotentCacheRedisTemplate implements IdempotentCache {


    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public void put(String key, int interval, TimeUnit unit) {

    }

    @Override
    public void put(String key, int interval) {

    }

    @Override
    public void clear() {

    }
}
