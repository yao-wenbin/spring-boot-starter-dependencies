package com.github.yaowenbin.idempotent.keyparse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
public class EmptyParser extends KeyParser {

    private final String EMPTY_STRING = "";

    @Override
    public boolean match(String key) {
        return !StringUtils.hasLength(key);
    }

    @Override
    public String doParse(String key, ProceedingJoinPoint jp) {
        return EMPTY_STRING;
    }
}
