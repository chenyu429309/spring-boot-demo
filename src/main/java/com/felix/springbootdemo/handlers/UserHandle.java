package com.felix.springbootdemo.handlers;

import com.alibaba.fastjson.JSON;
import com.felix.springbootdemo.interfaces.QueueConstants;
import com.felix.springbootdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 作者 : Felix
 * 创建时间 : 2018-09-13 11:27
 */
@Slf4j
@Component
@RabbitListener(queues = {QueueConstants.MESSAGE_QUEUE_NAME})
public class UserHandle {
    @RabbitHandler
    public void handler(@Payload User user) {
        log.info("消费内容：{}", user);
    }

}

