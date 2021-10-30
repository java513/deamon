package com.lh.validator.advice;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: deamon
 * @description: validator统一异常处理
 * @author: lh
 * @date: 2021-10-28 21:53
 **/
@ControllerAdvice
public class BindingExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handleException(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder("当前请求参数校验异常:");
        fieldErrors.forEach(field->sb.append(field.getField()).append(":").append(field.getDefaultMessage()));
        return sb.toString();
    }
}
