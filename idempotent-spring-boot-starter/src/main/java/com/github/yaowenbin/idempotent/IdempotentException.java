package com.github.yaowenbin.idempotent;

/**
 * @Author yaowenbin
 * @Date 2023/6/20
 */
public class IdempotentException extends RuntimeException{

    public IdempotentException(String err) {
        super(err);
    }

}
