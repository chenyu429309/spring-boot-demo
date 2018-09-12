package com.felix.springbootdemo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class BaseModel<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 7730914970725783794L;
    protected ID id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
