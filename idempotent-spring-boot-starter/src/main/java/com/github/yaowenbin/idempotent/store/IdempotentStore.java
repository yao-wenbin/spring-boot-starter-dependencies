package com.github.yaowenbin.idempotent.store;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
public interface IdempotentStore {

    boolean exists(String key);

    IdempotentStore put(String key);

    IdempotentStore put(String key, int interval, TimeUnit timeUnit);

    void clear();

    Boolean VALUE = Boolean.TRUE;

    // 使用方法的全限定名作为Key，比如说com.yaowenbin.idempotent.IdempotentInstance#dosomething.
    default String buildKey(Method method, String simpleKey) {
        return method.getDeclaringClass().getCanonicalName() + "#" + method.getName() + "#" + simpleKey;
    }

    default boolean exists(Method method, String simpleKey) {
        return exists(buildKey(method, simpleKey));
    }

    default IdempotentStore put(Method method, String simpleKey, int interval, TimeUnit unit) {
        return put(buildKey(method, simpleKey), interval, unit);
    }

    default IdempotentStore put(Method method, String simpleKey) {
        return put(buildKey(method, simpleKey));
    }


}
