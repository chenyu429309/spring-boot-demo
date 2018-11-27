package com.felix.springbootdemo.strategy;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.springbootdemo.entity.Users;
import com.felix.springbootdemo.enums.Sex;
import com.felix.springbootdemo.mapper.UsersMapper;
import org.springframework.stereotype.Service;

@Service
public class SmsOrderServiceImpl extends ServiceImpl<UsersMapper, Users> implements OrderService {

    @Override
    public void order(OrderReqParam orderReqParam) {
        Users users = new Users();
        users.setId(1).setVersion(3).setAge(2).setUserSex(Sex.MAN).setName(orderReqParam.toString());
        this.updateById(users);
    }
}

