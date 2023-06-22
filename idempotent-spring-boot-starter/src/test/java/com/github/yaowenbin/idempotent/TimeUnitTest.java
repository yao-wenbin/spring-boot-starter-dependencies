package com.github.yaowenbin.idempotent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
class TimeUnitTest {

    @Test
    void convert() {
        int interval = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        /**
         * error:
         * long convert = unit.convert(interval, TimeUnit.MILLISECONDS);
         */
        long convert = TimeUnit.MILLISECONDS.convert(interval, unit);

        Assertions.assertEquals(1000, convert);
    }
}
