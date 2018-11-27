package com.felix.springbootdemo.model;

import com.felix.springbootdemo.intercept.MyMethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
//@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //System.out.print("beanName:" + beanName + "执行..postProcessBeforeInstantiation\n");
        //利用 其 生成动态代理
        if (beanClass == Person.class) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MyMethodInterceptor());
            Person testFb = (Person) enhancer.create();
            System.out.print("返回动态代理\n");
            return testFb;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.out.print("beanName:" + beanName + "执行..postProcessAfterInstantiation\n");
        }
        return false;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.out.print("beanName:" + beanName + "执行..postProcessPropertyValues\n");
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.out.print("beanName:" + beanName + "执行..postProcessBeforeInitialization\n");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.out.print("beanName:" + beanName + "执行..postProcessAfterInitialization\n");
        }
        return bean;
    }
}