package com.github.yaowenbin.idempotent.keyparse;

import com.github.yaowenbin.idempotent.IdempotentException;
import com.github.yaowenbin.idempotent.IdempotentProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Slf4j
public class TokenParser extends SpelKeyParser{

    private final IdempotentProperties properties;

    public List<String> MATCH_KEYS = List.of("#token", "token");

    public TokenParser(BeanResolver beanResolver, IdempotentProperties properties) {
        super(beanResolver);
        this.properties = properties;
    }

    @Override
    public boolean match(String key) {
        String lowerCaseKey = key.toLowerCase(Locale.ROOT);
        return MATCH_KEYS.contains(lowerCaseKey);
    }

    @Override
    public String doParse(String key, ProceedingJoinPoint jp) {
        if (properties == null || !StringUtils.hasText(properties.getTokenExpression())) {
            log.error("cannot parse token key, because, properties or tokenExpression, is null.  {}", properties);
            throw new IdempotentException("cannot parse token key, because, properties or tokenExpression, is null.");
        }

        return super.doParse(properties.getTokenExpression(), jp);
    }
}
