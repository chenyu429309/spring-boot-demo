package com.felix.springbootdemo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.felix.springbootdemo.strategy.OrderReqParam;
import com.felix.springbootdemo.strategy.OrderService;
import com.felix.springbootdemo.strategy.SmsOrderServiceImpl;
import com.felix.springbootdemo.strategy.ZdhOrderServiceImpl;
import org.springframework.context.ApplicationContext;

/**
 * 测试枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AloneTpe implements IEnum<String> {
    sms{
        @Override
        public void order(OrderReqParam orderReqParam,ApplicationContext applicationContext) {
            applicationContext.getBean(SmsOrderServiceImpl.class).order(orderReqParam);
        }
    },
    zdh{
        @Override
        public void order(OrderReqParam orderReqParam,ApplicationContext applicationContext) {
            applicationContext.getBean(SmsOrderServiceImpl.class).order(orderReqParam);
        }
    };

    @Override
    public String getValue() {
        return null;
    }

    public abstract void order(OrderReqParam orderReqParam, ApplicationContext applicationContext);
}
