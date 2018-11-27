package com.felix.springbootdemo.strategy;

import com.felix.springbootdemo.enums.AloneTpe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel
@Data
@ToString
public class OrderReqParam {
    @ApiModelProperty("独立功能类型")
    private AloneTpe type;
    @ApiModelProperty("独立功能时长")
    private Integer time;
    @ApiModelProperty("独立功能条数")
    private Integer count;
}
