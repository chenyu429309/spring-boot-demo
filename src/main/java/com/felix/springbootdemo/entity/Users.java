package com.felix.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.felix.springbootdemo.enums.Sex;
import com.felix.springbootdemo.service.impl.UsersServiceImpl;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author felix
 * @since 2018-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Users对象", description = "")
@TableName(value = "public.users")
@Slf4j
@Component
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(select = false, exist = false)
    private Integer column3;
    @TableField(value = "age")
    private Integer age;
    @TableField(value = "user_sex")
    private Sex userSex;
    @TableField(value = "created_at")
    private LocalDateTime createdAt;
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;
    @Version
    @TableField(value = "version")
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public void init(ApplicationContext applicationContext) {
        log.error(applicationContext.getBean(UsersServiceImpl.class).toString());
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
