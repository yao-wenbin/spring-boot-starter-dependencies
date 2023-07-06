package io.github.yaowenbin.httplogging.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@Component
@RequestScope
@Data
public class LogEntity {

    private String path;
    private Map<String, String[]> params;
    private Object req;
    private Object resp;

}
