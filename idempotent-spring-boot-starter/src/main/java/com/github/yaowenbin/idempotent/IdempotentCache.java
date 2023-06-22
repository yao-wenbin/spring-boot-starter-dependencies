package com.github.yaowenbin.idempotent;

import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
public interface IdempotentCache {

    boolean exists(String key);

    void put(String key, int interval, TimeUnit unit);

    void put(String key, int interval);

    void clear();

}
