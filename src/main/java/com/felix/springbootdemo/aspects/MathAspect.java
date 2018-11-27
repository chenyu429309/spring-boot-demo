package com.felix.springbootdemo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Slf4j
public class MathAspect {
    @Pointcut("execution(public * com.felix.springbootdemo.test.*.*(..))")
    public void test() {
    }

    @Before("test()")
    public void doBefore(JoinPoint joinPoint) {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //获取参数
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        log.info("方法{}前置增强，参数为：{}", name, list);
    }

    @After("test()")
    public void doAfter(JoinPoint joinPoint) {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //获取参数
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        log.info("方法{}后置增强，参数为：{}", name, list);
    }

    @AfterReturning(value = "test()", returning = "returning")
    public void doAfterReturning(JoinPoint joinPoint, Object returning) {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //获取参数
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        log.info("方法{}返回增强，参数为：{},返回值为：{}", name, list, returning);
    }

    @AfterThrowing(value = "test()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //获取参数
        List<Object> list = Arrays.asList(joinPoint.getArgs());
        log.info("方法{}异常增强，参数为：{},异常为：{}", name, list, e.getMessage());
    }
}
