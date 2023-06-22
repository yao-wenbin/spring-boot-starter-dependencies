package com.github.yaowenbin.idempotent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
@ConfigurationProperties(prefix = "idempotent")
@Data
public class IdempotentProperties {

    private int interval;

}
