package com.felix.springbootdemo.strategy;

import com.felix.springbootdemo.enums.AloneTpe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;

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
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("重复输入密码")
    private String rePwd;

    @AssertTrue(message = "密码两次输入不一致")
    private boolean isRePwd() {
        return this.pwd.equals(this.rePwd);
    }
}
