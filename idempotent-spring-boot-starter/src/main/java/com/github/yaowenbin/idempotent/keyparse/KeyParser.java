package com.github.yaowenbin.idempotent.keyparse;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
public abstract class KeyParser {

    private KeyParser nextParser;

    /**
     * 链式调用责任链
     * @param parser 下一个解析器
     * @return 下一个解析器
     */
    public KeyParser next(KeyParser parser) {
        nextParser = parser;
        return nextParser;
    }

    public String parseKey(String key, ProceedingJoinPoint jp) {
        if (!match(key)) {
            return nextParser.parseKey(key, jp);
        }
        String result = doParse(key, jp);
        if (result == null && nextParser != null) {
            result = nextParser.parseKey(key, jp);
        }

        // when result == null && next parse == null, it will return null.
        return result;
    }

    /**
     * 匹配条件，只有匹配成功才会走当前解析器，否则走下一级解析器.
     * @param key 注解中的key
     * @return 是否匹配
     */
    public abstract boolean match(String key);

    public abstract String doParse(String key, ProceedingJoinPoint jp);


}
