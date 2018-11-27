package com.felix.springbootdemo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sex implements IEnum<String> {
    WOMEN,
    MAN;

    @Override
    public String getValue() {
        return null;
    }
}
