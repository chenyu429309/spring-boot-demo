package com.felix.springbootdemo.config;

import com.felix.springbootdemo.aspects.MathAspect;
import com.felix.springbootdemo.test.MathTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public MathTest mathTest() {
        return new MathTest();
    }

    @Bean
    public MathAspect mathAspect() {
        return new MathAspect();
    }
}
