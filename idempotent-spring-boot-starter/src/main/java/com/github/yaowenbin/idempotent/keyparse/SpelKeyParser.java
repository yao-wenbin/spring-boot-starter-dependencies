package com.github.yaowenbin.idempotent.keyparse;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 * SpEL表达式的用法参考https://juejin.cn/post/6867179655934410766
 */
@RequiredArgsConstructor
public class SpelKeyParser extends KeyParser {

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    private final BeanResolver beanResolver;

    @Override
    public boolean match(String key) {
        return true;
    }

    @Override
    public String doParse(String key, ProceedingJoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Object [] args = jp.getArgs();

        StandardEvaluationContext context = new MethodBasedEvaluationContext(jp, method, args, NAME_DISCOVERER);
        context.setBeanResolver(beanResolver);

        final Object value = PARSER.parseExpression(key).getValue(context);
        return value == null ? null : value.toString();
    }


}
