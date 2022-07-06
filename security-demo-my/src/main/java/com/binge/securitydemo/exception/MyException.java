package com.binge.securitydemo.exception;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-02 16:26
 **/
public class MyException extends RuntimeException {

    protected final String message;

    public MyException(String message) {
        this.message = message;
    }

    public MyException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
