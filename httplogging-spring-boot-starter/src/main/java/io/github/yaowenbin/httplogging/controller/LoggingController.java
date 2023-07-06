package io.github.yaowenbin.httplogging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@RestController
public class LoggingController {

    @GetMapping("/logging")
    public Map<String, Object> getRequest(Map<String, Object> req) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "success");
        map.put("data", 100);
        return map;
    }

    @PostMapping("/logging")
    public Map<String, Object> postRequest(Map<String, Object> req) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "success");
        map.put("data", 200);
        return map;
    }

}
