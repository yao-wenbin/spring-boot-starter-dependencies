package io.github.yaowenbin.httplogging.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author yaowenbin
 * @Date 2023/6/26
 */
@SpringBootTest
@AutoConfigureMockMvc
class LoggingControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void getRequest() throws Exception{
        mock.perform(get("/logging").param("123", "123"))
                .andExpect(status().isOk());

        mock.perform(post("/logging").param("123", "123"))
                .andExpect(status().isOk());

    }

}