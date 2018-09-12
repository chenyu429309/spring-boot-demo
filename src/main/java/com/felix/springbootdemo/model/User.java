package com.felix.springbootdemo.model;

import com.felix.springbootdemo.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel<Integer> {
    private static final long serialVersionUID = 8097142134973105548L;
    private String name;
    private Integer age;
    private UserSexEnum userSex;
}

