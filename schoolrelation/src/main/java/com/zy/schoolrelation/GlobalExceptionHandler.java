package com.zy.schoolrelation;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局捕获异常
 */
@ControllerAdvice
@RequestMapping(produces = "application/json;charset=UTF-8")
public class GlobalExceptionHandler {
/*
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ErrorResult exceptionHandler(){
        ErrorResult error = ErrorResult.builder().code("100").retMsg("error").build();
        return error;
    }*/
}
