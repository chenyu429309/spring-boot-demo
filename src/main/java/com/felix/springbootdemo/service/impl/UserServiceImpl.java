package com.felix.springbootdemo.service.impl;

import com.felix.springbootdemo.interfaces.QueueConstants;
import com.felix.springbootdemo.mapper.UserMapper;
import com.felix.springbootdemo.model.User;
import com.felix.springbootdemo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author felix
 */

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Cacheable(value = "user", key = "'test'+#id", unless = "#result!=null")
    @Override
    public User getUser(Integer id) {
        Optional<User> user = Optional.ofNullable(userMapper.getOne(id));
        this.rabbitTemplate.convertAndSend(QueueConstants.MESSAGE_QUEUE_NAME, user.orElse(new User()));
        return user.orElse(null);
    }

    @Override
    public PageInfo<User> getAll() {
        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10)
                .setOrderBy("id desc").doSelectPageInfo(() -> this.userMapper.getAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<User> userPageInfo = new PageInfo<>(this.userMapper.getAll());
        log.info("[普通写法] - [{}]", userPageInfo);

        return userPageInfo;
    }
}
