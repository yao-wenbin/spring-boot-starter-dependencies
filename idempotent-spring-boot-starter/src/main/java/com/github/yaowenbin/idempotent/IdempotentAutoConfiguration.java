package com.github.yaowenbin.idempotent;

import com.github.yaowenbin.idempotent.keyparse.EmptyParser;
import com.github.yaowenbin.idempotent.keyparse.KeyParser;
import com.github.yaowenbin.idempotent.keyparse.SpelKeyParser;
import com.github.yaowenbin.idempotent.keyparse.TokenParser;
import com.github.yaowenbin.idempotent.store.IdempotentStore;
import com.github.yaowenbin.idempotent.store.IdempotentStoreCaffeine;
import com.github.yaowenbin.idempotent.store.IdempotentStoreRedisTemplate;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@EnableConfigurationProperties(IdempotentProperties.class)
@EnableAspectJAutoProxy
public class IdempotentAutoConfiguration {

    @Autowired
    IdempotentProperties properties;

    @Bean
    @ConditionalOnMissingBean
    IdempotentStore idempotentCache(ApplicationContext context) {
        return chooseCacheFromIOC(properties, context);

    }

    private IdempotentStore chooseCacheFromIOC(IdempotentProperties properties ,ApplicationContext ctx) {
        if (ctx.containsBean(properties.getRedisTemplate())) {
            RedisTemplate redisTemplate = ctx.getBean(properties.getRedisTemplate(), RedisTemplate.class);
            return new IdempotentStoreRedisTemplate(redisTemplate);
        }
        return new IdempotentStoreCaffeine(properties);
    }


    /**
     *
     * parser chain: empty -> token -> spel
     */
    @Bean
    @ConditionalOnMissingBean
    public KeyParser keyParser(IdempotentProperties properties, BeanFactory beanFactory) {
        BeanFactoryResolver beanResolver = new BeanFactoryResolver(beanFactory);

        EmptyParser emptyParser = new EmptyParser();
        TokenParser tokenParser = new TokenParser(beanResolver, properties);
        SpelKeyParser spelKeyParser = new SpelKeyParser(beanResolver);

        emptyParser.next(tokenParser).next(spelKeyParser);

        return emptyParser;
    }


    @Bean
    IdempotentAspect idempotentAspect(IdempotentStore cache, KeyParser keyParser) {
        return new IdempotentAspect(cache, keyParser);
    }



}
