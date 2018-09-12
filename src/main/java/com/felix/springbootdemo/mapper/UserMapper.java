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
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class)
    })
    List<User> getAll();

    @Select("SELECT * FROM public.users WHERE id = #{id}")
    User getOne(@Param("id") Integer id);

    @Insert("INSERT INTO public.users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE public.users SET userName=#{name},age=#{age} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM public.users WHERE id =#{id}")
    void delete(Integer id);

}