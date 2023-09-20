package com.example.warehouse.exception;

/**
 * 自定义运行时的异常
 */
public class BusinessException extends RuntimeException {
    // 只是创建了异常对象
    public BusinessException() {
        super();
    }

    // 创建了异常对象同时指定异常信息
    public BusinessException(String message) {
        super(message);
    }
}
