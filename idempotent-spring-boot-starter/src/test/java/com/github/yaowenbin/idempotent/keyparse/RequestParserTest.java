package com.github.yaowenbin.idempotent.keyparse;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
class RequestParserTest {

    RequestParser parser = new RequestParser();

    @Test
    void realKey() {
        String realKey = parser.realKey(RequestParser.REQUEST_PREFIX.concat("123123"));

        assertThat(realKey).isEqualTo("123123");
    }
}