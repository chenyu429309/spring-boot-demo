package com.felix.springbootdemo.handlers;

import com.felix.springbootdemo.model.ResponseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleBindException(BindException e) {
        List<Map<String, Object>> errors = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            errors = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                Map<String, Object> error = new HashMap<>();
                error.put("key", fieldError.getField());
                error.put("message", fieldError.getDefaultMessage());
                errors.add(error);
            }
        }
        return errors;
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
