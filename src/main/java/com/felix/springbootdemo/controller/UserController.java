package com.felix.springbootdemo.controller;

import com.felix.springbootdemo.model.ResponseModel;
import com.felix.springbootdemo.model.User;
import com.felix.springbootdemo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get_user/{id}")
    @ApiOperation(value = "主键查询（DONE）")
    public ResponseModel<User> getUser(@PathVariable(value = "id") String id) {
        return ResponseModel.ok(userService.getUser(Integer.parseInt(id)));
    }

    @GetMapping(value = "/get_all")
    @ApiOperation(value = "全部查询（DONE）")
    public ResponseModel<PageInfo> getUser() {
        return ResponseModel.ok(userService.getAll());
    }

}
