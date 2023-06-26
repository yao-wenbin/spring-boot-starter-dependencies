package com.github.yaowenbin.idempotent.store;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.github.yaowenbin.idempotent.IdempotentProperties;

import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
public class IdempotentStoreCaffeine implements IdempotentStore {

    private final Boolean value = Boolean.TRUE;

    private final TimedCache<String/* method + key */, Boolean /* expiredTime */> cache;

    public IdempotentStoreCaffeine(IdempotentProperties properties) {
        int interval = properties.getInterval();
        TimeUnit timeUnit = properties.getTimeUnit();

        cache = CacheUtil.newTimedCache(convertMillSeconds(interval, timeUnit));
        // 取消定时清理
        cache.cancelPruneSchedule();

    }

    @Override
    public boolean exists(String key) {
        return value.equals(cache.get(key));
    }

    @Override
    public IdempotentStore put(String key) {
        cache.put(key, value);
        return this;
    }

    @Override
    public IdempotentStore put(String key, int interval, TimeUnit timeUnit) {
        cache.put(key, value, convertMillSeconds(interval, timeUnit));
        return this;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    long convertMillSeconds(int interval, TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(interval, unit);
    }
}
