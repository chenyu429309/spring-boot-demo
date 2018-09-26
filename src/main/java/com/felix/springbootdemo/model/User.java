package com.felix.springbootdemo.model;

import com.felix.springbootdemo.enums.UserSexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User extends BaseModel<Integer> {
    private static final long serialVersionUID = 8097142134973105548L;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性别")
    private UserSexEnum userSex;
}

