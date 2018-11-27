package com.felix.springbootdemo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean, BeanPostProcessor, InstantiationAwareBeanPostProcessor {
    private String name;
    private BeanFactory beanFactory;
    private String beanName;
    @Autowired
    private ApplicationContext applicationContext;

    public void setName(String name) {
        log.info("[setName] :" + name);
        this.name = name;
    }

    @PostConstruct //初始化方法的注解方式  等同与init-method=init
    public void init() {
        Person person=applicationContext.getBean(Person.class);
        person.toString();
        log.info("调用初始化方法....");
    }

    @PreDestroy    //销毁方法的注解方式  等同于destory-method=destory222
    public void Mydestroy() {
        log.info("调用销毁化方法....");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("[BeanFactory] :" + beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        log.info("[BeanName] :" + name);
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        log.info("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    /**
     * 预初始化 初始化之前调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("SpringLifeCycleProcessor start beanName={}", beanName);
        return bean;
    }

    /**
     * 后初始化  bean 初始化完成调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
