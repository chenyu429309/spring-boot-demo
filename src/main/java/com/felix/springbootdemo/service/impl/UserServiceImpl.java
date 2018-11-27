//package com.felix.springbootdemo.service.impl;
//
//import com.felix.springbootdemo.config.UserListenEvent;
//import com.felix.springbootdemo.interfaces.QueueConstants;
//import com.felix.springbootdemo.mapper.UserMapper;
//import com.felix.springbootdemo.model.PageData;
//import com.felix.springbootdemo.model.User;
//import com.felix.springbootdemo.service.UserService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.Optional;
//
///**
// * @author felix
// */
//
//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//@Service
//@Slf4j
//@CacheConfig(cacheNames = "user")
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserMapper userMapper;
//
//    private final RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public UserServiceImpl(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Cacheable(unless = "#result == null", key = "#id")
//    @Override
//    public User getUser(Integer id) {
//        Optional<User> user = Optional.ofNullable(userMapper.getOne(id));
//        applicationContext.publishEvent(new UserListenEvent(this, user.orElse(null)));
//        String[] str = new String[]{
//                "52179", "53529", "52153", "52205", "51631", "52193", "53555", "51897", "53531", "52718", "52154", "53554", "51926"};
//        Arrays.stream(str).forEach(s -> {
//            this.rabbitTemplate.convertAndSend(QueueConstants.MESSAGE_QUEUE_NAME, s);
//        });
//        return user.orElse(null);
//    }
//
//    @Cacheable(unless = "#result == null", key = "'all'")
//    @Override
//    public PageInfo<User> getAll(PageData pageData) {
//        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
//        final PageInfo<User> pageInfo = PageHelper.startPage(pageData.getPageNum(), pageData.getPageSize())
//                .setOrderBy(pageData.getOrderBy()).doSelectPageInfo(() -> this.userMapper.getAll(pageData));
//        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());
//
////        PageHelper.startPage(1, 10).setOrderBy("id desc");
////        final PageInfo<User> userPageInfo = new PageInfo<>(this.userMapper.getAll());
////        log.info("[普通写法] - [{}]", userPageInfo);
//
//        return pageInfo;
//    }
//
//    @Transactional
//    @Caching(
//            cacheable = {@Cacheable(key = "#user.id")},
//            evict = {@CacheEvict(key = "'all'")}
//    )
//    @Override
//    public User insert(User user) {
//        this.userMapper.insert(user);
//        return user;
//    }
//
//    @Transactional
////    @CachePut(key = "#user.id")
//    @Caching(
//            put = {@CachePut(key = "#user.id")},
//            evict = {@CacheEvict(key = "'all'")}
//    )
//    @Override
//    public User update(User user) {
//        this.userMapper.update(user);
//        return user;
//    }
//
//    @Transactional
//    @CacheEvict(key = "#id")
//    @Override
//    public User delete(Integer id) {
//        User user = getUser(id);
//        this.userMapper.delete(id);
//        return user;
//    }
//}
