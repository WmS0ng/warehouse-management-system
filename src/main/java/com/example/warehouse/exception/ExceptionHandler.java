package com.example.warehouse.exception;

import com.example.warehouse.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义全局异常处理
 */
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        return Result.err(Result.CODE_ERR_SYS, e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result BusinessException(BusinessException e) {
        return Result.err(Result.CODE_ERR_BUSINESS, e.getMessage());
    }
}
