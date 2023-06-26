package com.github.yaowenbin.idempotent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@ConfigurationProperties(prefix = "idempotent")
@Data
public class IdempotentProperties {

    private int interval = 500;

    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /***
     * 从当前IOC容器中获取token的表达式
     */
    private String tokenExpression;

}
