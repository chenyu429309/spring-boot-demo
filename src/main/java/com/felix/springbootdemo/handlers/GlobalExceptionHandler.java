package com.felix.springbootdemo.handlers;

import com.felix.springbootdemo.model.ResponseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
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
        log.error("{}", exception);
        return new ResponseModel().setKey(ResponseModel.ERROR)
                .setMsg(message);
    }
}
