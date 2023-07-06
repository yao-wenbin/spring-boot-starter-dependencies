package com.github.yaowenbin.idempotent.keyparse;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Slf4j
public class RequestParser extends KeyParser {

    public static final String REQUEST_PREFIX = "#request:";
    public static final int REQUEST_PREFIX_LEN = REQUEST_PREFIX.length();

    @Override
    public boolean match(String key) {
        return REQUEST_PREFIX.equals(key.toLowerCase(Locale.ROOT));
    }

    @Override
    public String doParse(String key, ProceedingJoinPoint jp) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.error("not in servlet web environment, cannot get request");
            return "";
        }
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getParameter(realKey(key));
    }

    public String realKey(String key) {
        return key.substring(REQUEST_PREFIX_LEN);
    }
}
