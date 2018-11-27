package com.felix.springbootdemo.interfaces;

public interface QueueConstants {
    /**
     * 消息交换
     */
    String MESSAGE_EXCHANGE = "hat.direct.exchange";
    /**
     * 消息队列名称
     */
    String MESSAGE_QUEUE_NAME = "hat_api.insert_landing_page.queue";
    /**
     * 消息路由键
     */
    String MESSAGE_ROUTE_KEY = "hat_api.insert_landing_page.queue";
}