package com.github.yaowenbin.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Idempotent {

    /**
     * Key
     */
    String key() default "";

    /**
     * 重复提交间隔
     */
    int interval() default 500;

    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 错误信息
     */
    String errMsg() default "重复提交请求";

}
