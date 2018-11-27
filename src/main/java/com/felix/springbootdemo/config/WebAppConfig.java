package com.felix.springbootdemo.config;

import com.felix.springbootdemo.intercept.LoginIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig {
    @Autowired
    private LoginIntercept loginIntercept;

    @Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(loginIntercept).addPathPatterns("/**")
//                        .excludePathPatterns("/static/**");
            }
        };
    }

}
