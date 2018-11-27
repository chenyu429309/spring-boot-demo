package com.felix.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.springbootdemo.entity.Users;
import com.felix.springbootdemo.enums.Sex;
import com.felix.springbootdemo.model.PageData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * mapper 接口
 * </p>
 *
 * @author felix
 * @since 2018-10-28
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    int updateByName(@Param("name") String name);

    List<Users> selectByName(@Param("Name") String Name);

    @Select("SELECT * FROM public.users")
    @Results(id = "userMapper", value = {
            @Result(property = "userSex", column = "user_sex", javaType = Sex.class)
    })
    List<Users> getAll(@Param("pageData") PageData pageData);
//
//    @Select("SELECT * FROM public.users WHERE id = #{id}")
//    @ResultMap("userMapper")
//    User getOne(@Param("id") Integer id);
//
//    @Insert("INSERT INTO public.users(id,name,age,user_sex,created_at,updated_at) VALUES(#{id},#{name}, #{age}, #{userSex},#{createdAt},#{updatedAt})")
//    void insert(User user);
//
//    @Update("UPDATE public.users SET name=#{name},age=#{age} WHERE id =#{id}")
//    void update(User user);
//
//    @Delete("DELETE FROM public.users WHERE id =#{id}")
//    void delete(@Param("id") Integer id);
}
