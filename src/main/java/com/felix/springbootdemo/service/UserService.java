package com.felix.springbootdemo.service;

import com.felix.springbootdemo.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    User getUser(Integer id);

    PageInfo<User> getAll();
}
