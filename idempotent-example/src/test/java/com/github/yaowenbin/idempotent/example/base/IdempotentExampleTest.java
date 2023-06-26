package com.github.yaowenbin.idempotent.example.base;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */


@SpringBootTest(classes = IdempotentExampleTest.IdempotentExampleApplication.class)
@ExtendWith(SpringExtension.class)
public class IdempotentExampleTest implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("测试结束");
    }

    @SpringBootApplication
    static class IdempotentExampleApplication {
        public static void main(String... args) {
            SpringApplication.run(IdempotentExampleTest.class, args);
        }
    }


}
