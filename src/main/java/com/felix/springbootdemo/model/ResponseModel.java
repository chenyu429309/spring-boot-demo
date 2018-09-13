package com.felix.springbootdemo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class ResponseModel<T> {
    public static final String OK = "ok";
    public static final String ERROR = "error";
    private String key;
    private String msg;
    private T t;

    public static ResponseModel ok(Object o) {
        return new ResponseModel().setT(o).setKey(OK);
    }
}
