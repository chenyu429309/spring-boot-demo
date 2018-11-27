package com.felix.springbootdemo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("插入方法实体填充");
        setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        System.out.println("更新方法实体填充");
    }
}
