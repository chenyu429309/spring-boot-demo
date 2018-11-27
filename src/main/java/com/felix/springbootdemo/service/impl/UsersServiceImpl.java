package com.felix.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.springbootdemo.entity.Users;
import com.felix.springbootdemo.enums.Sex;
import com.felix.springbootdemo.mapper.UsersMapper;
import com.felix.springbootdemo.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author felix
 * @since 2018-10-28
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    /**
     * spring事务
     *  1：一定要将异常抛出
     *  2：在用一个类中相互调用时，需要使用aop代理，或者在本类中注入自己，或者使用applicationContext.getBean("XXXX")
     */
    @Transactional(rollbackFor = Exception.class)
    public void B(){
        try {
            Users user=new Users();
            user.setAge(1);
            user.setName("test");
            user.setUserSex(Sex.MAN);
            user.setVersion(3);
            user.setId(1);
            this.updateById(user);
            int i=1/0;
        }catch (Exception e ){
            throw  new RuntimeException("测试错误");
        }
    }

//    public void B(){
//        Aops.getSelf(this).A();
//    }
}
