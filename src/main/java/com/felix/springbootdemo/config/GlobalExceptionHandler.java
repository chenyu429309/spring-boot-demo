package com.felix.springbootdemo.config;

import com.felix.springbootdemo.model.ResponseModel;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Data
public class GlobalExceptionHandler {
    private HttpServletRequest request;
    private String message;
    private Exception exception;

    @ExceptionHandler(Exception.class)
    public ResponseModel exceptionHandler(HttpServletRequest request, Exception exception) {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    private ResponseModel handleErrorInfo(HttpServletRequest request, String message, Exception exception) {
        this.request = request;
        this.message = message;
        this.exception = exception;
        return new ResponseModel().setKey(ResponseModel.ERROR)
                .setMsg(message);
    }
}
