package io.github.yaowenbin.httplogging.config;

import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */

@Component
public class HttpLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

    @Autowired
    LogEntity logEntity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logEntity.setParams(request.getParameterMap());
        logEntity.setPath(request.getServletPath());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("controller 请求日志：{}", JSON.toJSONString(logEntity));
    }


}
