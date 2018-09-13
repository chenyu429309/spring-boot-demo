package com.felix.springbootdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel
public abstract class BaseModel<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 7730914970725783794L;
    protected ID id;
    @ApiModelProperty("创建时间")
    protected LocalDateTime createdAt;
    @ApiModelProperty("更新时间")
    protected LocalDateTime updatedAt;
}
