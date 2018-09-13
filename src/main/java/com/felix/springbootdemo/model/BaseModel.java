package com.felix.springbootdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel
public abstract class BaseModel<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 7730914970725783794L;
    @ApiModelProperty("主键")
    protected ID id;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;
}
