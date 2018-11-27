package com.felix.springbootdemo.strategy;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.springbootdemo.entity.Users;

public interface OrderService extends IService<Users> {
    void order(OrderReqParam orderReqParam);
}
