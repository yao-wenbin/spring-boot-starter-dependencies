package com.github.yaowenbin.idempotent;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@Aspect
@Configuration
@Slf4j
public class IdempotentAspect {

    private final IdempotentCache cache;

    public IdempotentAspect(IdempotentCache cache) {
        this.cache = cache;
    }

    @Pointcut("@annotation(com.github.yaowenbin.idempotent.Idempotent)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] methodParams = parseMethodParams(method);
        Object [] args = joinPoint.getArgs();
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        // String result = parseExpression(idempotent.valExpression(), methodParams, args);

        // String key = method.getName().concat(result);

        // 使用方法的全限定名作为Key，比如说com.yaowenbin.idempotent.IdempotentInstance#dosomething.
        String key = signature.getDeclaringTypeName() + "#" + method.getName();

        if (cache.exists(key)) {
            throw new IdempotentException("重复提交请求");
        }

        log.info("未找到重复请求，加入重复请求Map");
        cache.put(key, idempotent.interval(), idempotent.unit());

        return joinPoint.proceed();
    }

    private String[] parseMethodParams(Method method) {
        //  获取方法的参数名列表
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        return discoverer.getParameterNames(method);
    }

    private String parseExpression(String expressionString, String[] methodParams, Object[] args) {
        //SPEL解析
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < methodParams.length; i++) {
            context.setVariable(methodParams[i], args[i]);
        }
        String result = parser.parseExpression(expressionString).getValue(context, String.class);
        return result;
    }


}
