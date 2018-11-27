package com.felix.springbootdemo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class logAspect {
    @Pointcut("execution(public * com.felix.springbootdemo.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void dobefore(JoinPoint joinPoint) {
        //获取目标方法的参数
        Object[] args = joinPoint.getArgs();
        //获取当前方法
        Signature signature = joinPoint.getSignature();
        log.info("当前方法的名字：{}", signature.getName());
        log.info("当前方法的Modifiers:{}", signature.getModifiers());
        log.info("当前方法的DeclaringType:{}", signature.getDeclaringType());
        log.info("当前方法的DeclaringTypeName:{}", signature.getDeclaringTypeName());
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        System.out.println("参数名：" + Arrays.toString(strings));
        System.out.println("参数值ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        // 记录下请求内容
        log.info("请求URL : {}", req.getRequestURL().toString());
        log.info("HTTP_METHOD : {} ", req.getMethod());
        log.info("IP : {}", req.getRemoteAddr());
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
