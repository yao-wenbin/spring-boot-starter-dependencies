package com.github.yaowenbin.idempotent;

import com.github.yaowenbin.idempotent.keyparse.KeyParser;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@Aspect
@Configuration
@Slf4j
@RequiredArgsConstructor
public class IdempotentAspect {

    private final IdempotentStore store;

    private final KeyParser parser;

    @Pointcut("@annotation(com.github.yaowenbin.idempotent.Idempotent)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        String key = parser.parseKey(idempotent.key(), joinPoint);

        if (store.exists(method, key)) {
            throw new IdempotentException("重复提交请求");
        }

        log.info("key: {}, 加入重复请求Store", key);
        store.put(method, key, idempotent.interval(), idempotent.unit());

        return joinPoint.proceed();
    }

}
