package com.felix.springbootdemo.mapper;

import com.felix.springbootdemo.enums.UserSexEnum;
import com.felix.springbootdemo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM public.users")
    @Results(id = "userMapper", value = {
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "updatedAt", column = "updated_at", javaType = LocalDateTime.class),
            @Result(property = "createdAt", column = "created_at", javaType = LocalDateTime.class)
    })
    List<User> getAll();

    @Select("SELECT * FROM public.users WHERE id = #{id}")
    @ResultMap("userMapper")
    User getOne(@Param("id") Integer id);

    @Insert("INSERT INTO public.users(id,name,age,user_sex,created_at,updated_at) VALUES(#{id},#{name}, #{age}, #{userSex},#{createdAt},#{updatedAt})")
    void insert(User user);

    @Update("UPDATE public.users SET name=#{name},age=#{age} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM public.users WHERE id =#{id}")
    User delete(Integer id);

}