package com.fb.xujimanage.exception;

/**
 * @program: xujimanage
 * @description: 自定义校验异常类
 * @author: chengjie
 * @date: Created in 2020/8/27 17:29
 **/
public class VerifyException extends RuntimeException {
    public VerifyException() {
        super();
    }

    //用详细信息指定一个异常
    public VerifyException(String message) {
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
