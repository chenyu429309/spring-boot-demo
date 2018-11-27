package com.felix.springbootdemo.config;

import com.felix.springbootdemo.test.MathTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        MathTest bean = applicationContext.getBean(MathTest.class);
        log.info("{}", bean);
        bean.div(1, 1);
    }
}
