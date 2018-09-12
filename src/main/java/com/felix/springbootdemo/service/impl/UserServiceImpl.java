package com.felix.springbootdemo.service.impl;

import com.felix.springbootdemo.mapper.UserMapper;
import com.felix.springbootdemo.model.User;
import com.felix.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "user", key = "'test'+#id", unless = "#result!=null")
    @Override
    public User getUser(Integer id) {
        Optional<User> user = Optional.ofNullable(userMapper.getOne(id));
        return user.orElse(null);
    }
}
