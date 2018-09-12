package com.felix.springbootdemo.controller;

import com.felix.springbootdemo.model.ResponseModel;
import com.felix.springbootdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.felix.springbootdemo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get_user/{id}")
    public ResponseModel<User> getUser(@PathVariable(value = "id") String id) {
        return ResponseModel.ok(userService.getUser(Integer.parseInt(id)));
    }

}
