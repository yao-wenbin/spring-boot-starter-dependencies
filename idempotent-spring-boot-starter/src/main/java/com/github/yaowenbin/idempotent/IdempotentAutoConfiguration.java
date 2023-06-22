package com.github.yaowenbin.idempotent;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@EnableConfigurationProperties(IdempotentProperties.class)
@EnableAspectJAutoProxy
public class IdempotentAutoConfiguration {

    @Bean
    IdempotentCache idempotentCache(ApplicationContext ctx) {
        return chooseCacheFromIOC(ctx);
    }

    private IdempotentCache chooseCacheFromIOC(ApplicationContext ctx) {
        // ctx.containsBean();
        return new IdempotentCacheLocalMap();
    }

    @Bean
    IdempotentAspect idempotentAspect(IdempotentCache cache) {
        return new IdempotentAspect(cache);
    }

}
