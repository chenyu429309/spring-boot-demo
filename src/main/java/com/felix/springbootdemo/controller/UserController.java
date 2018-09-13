package com.felix.springbootdemo.controller;

import com.felix.springbootdemo.model.ResponseModel;
import com.felix.springbootdemo.model.User;
import com.felix.springbootdemo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Slf4j
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get_user/{id}")
    @ApiOperation(value = "主键查询（DONE）")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path")})
    public ResponseModel<User> getUser(@PathVariable(value = "id") Integer id) {
        return ResponseModel.ok(userService.getUser(id));
    }

    @GetMapping(value = "/get_all")
    @ApiOperation(value = "全部查询（DONE）")
    public ResponseModel<PageInfo> getAll() {
        return ResponseModel.ok(userService.getAll());
    }

    @GetMapping(value = "/update")
    @ApiOperation(value = "全部查询（DONE）")
    public ResponseModel<User> update(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult.getFieldError().getDefaultMessage());
            return ResponseModel.error(bindingResult.getFieldError());
        }
        return ResponseModel.ok(userService.update(user));
    }

    @GetMapping(value = "/insert")
    @ApiOperation(value = "全部查询（DONE）")
    public ResponseModel<Integer> insert(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult.getFieldError().getDefaultMessage());
            return ResponseModel.error(bindingResult.getFieldError());
        }
        return ResponseModel.ok(userService.insert(user));
    }

    @GetMapping(value = "/delete/{id}")
    @ApiOperation(value = "全部查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path")})
    public ResponseModel<User> delete(@PathVariable("id") Integer id) {
        return ResponseModel.ok(userService.delete(id));
    }


}
