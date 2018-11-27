package com.felix.springbootdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.felix.springbootdemo.aspects.TransactionalTest;
import com.felix.springbootdemo.entity.Users;
import com.felix.springbootdemo.enums.AloneTpe;
import com.felix.springbootdemo.enums.Sex;
import com.felix.springbootdemo.service.UsersService;
import com.felix.springbootdemo.strategy.OrderReqParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author felix
 * @since 2018-10-28
 */
@RestController
@RequestMapping("/java/users")
@Slf4j
public class UsersController extends ApiController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping(value = "/get/{name}")
    @ApiOperation(value = "主键查询（DONE）")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "用户ID", dataType = "string", paramType = "path")})
    public R<List<Map<String, Object>>> get(@PathVariable(value = "name")String name) {
        List<Map<String, Object>> maps = usersService.listMaps(new QueryWrapper<Users>()
                .lambda().like(Users::getName, name)
                .or(e -> e.like(Users::getName, name)));
        return success(maps);
    }

    @PostMapping(value = "/update")
    @TransactionalTest
    public void update(){
        Users user=new Users();
        user.setAge(1);
        user.setName("test");
        user.setUserSex(Sex.MAN);
        user.setVersion(1);
        boolean b = usersService.saveOrUpdate(user);

    }
    @PostMapping(value = "/insert")
    @ApiOperation(value = "插入（DONE）")
    public R<Object> insert(@Valid OrderReqParam orderReqParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("{}", bindingResult.getFieldError().getDefaultMessage());
            return failed(bindingResult.getFieldError().getDefaultMessage());
        }
        AloneTpe.valueOf(orderReqParam.getType().name()).order(orderReqParam,applicationContext);
        return success("SUCCESS");
    }

    @PostMapping(value = "/test")
    public void test(){
        usersService.B();
    }
    @PutMapping(value = "/update")
    public R<Object> updateByLuckLock(){
        Users user=new Users();
        user.setId(1);
        user.setAge(1);
        user.setName("test");
        user.setUserSex(Sex.WOMEN);
        user.setVersion(1);
        user.init(applicationContext);
        boolean b=usersService.updateById(user);
        if(b){
            return success(user);
        }else{
            return failed("update failed");
        }
    }
}
