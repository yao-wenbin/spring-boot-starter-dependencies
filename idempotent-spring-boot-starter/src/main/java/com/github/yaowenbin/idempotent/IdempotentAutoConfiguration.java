package com.github.yaowenbin.idempotent;

import com.github.yaowenbin.idempotent.keyparse.KeyParser;
import com.github.yaowenbin.idempotent.keyparse.SpelKeyParser;
import com.github.yaowenbin.idempotent.keyparse.TokenParser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;

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
        if (ctx.containsBean(Const.REDIS_TEMPLATE)) {
            // return IdempotentCacheRedisTemplate();
        }
        return new IdempotentCacheLocalMap();
    }

    @Bean
    @ConditionalOnMissingBean
    public KeyParser keyParser(IdempotentProperties properties, BeanFactory beanFactory, ApplicationContext applicationContext) {
        BeanFactoryResolver beanResolver = new BeanFactoryResolver(beanFactory);
        TokenParser tokenParser = new TokenParser(beanResolver, properties);
        SpelKeyParser spelKeyParser = new SpelKeyParser(beanResolver);

        tokenParser.next(spelKeyParser);
        return tokenParser;
    }


    @Bean
    IdempotentAspect idempotentAspect(IdempotentCache cache, KeyParser keyParser) {
        return new IdempotentAspect(cache, keyParser);
    }



}
