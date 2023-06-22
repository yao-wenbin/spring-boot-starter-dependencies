package com.github.yaowenbin.idempotent;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
public class IdempotentCacheLocalMap implements IdempotentCache{

    private Map<String, Boolean> cache = new ConcurrentHashMap<>();

    private Timer timer = new Timer("IdempotentTimer");

    @Override
    public boolean exists(String key) {
        return cache.containsKey(key);
    }

    @Override
    public void put(String key, int interval, TimeUnit unit) {
        cache.put(key, true);
        long millInterval = TimeUnit.MILLISECONDS.convert(interval, unit);
        // delete after interval using timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cache.remove(key);
            }
        }, millInterval);
    }

    @Override
    public void put(String key, int interval) {

    }

    @Override
    public void clear() {
        cache.clear();
    }
}
