package com.felix.springbootdemo.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    public static final String OK = "ok";
    public static final String ERROR = "error";
    private String key;
    private String msg;
    private T t;

    public static ResponseModel ok(Object o) {
        return new ResponseModel().setT(o).setMsg(o.toString()).setKey(OK);
    }
}
