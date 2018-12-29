package com.felix.springbootdemo.designPatterns.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibIntroductionBrowserProxy implements MethodInterceptor, Game {

    private static CglibIntroductionBrowserProxy proxy = new CglibIntroductionBrowserProxy();

    private CglibIntroductionBrowserProxy() {

    }

    public static CglibIntroductionBrowserProxy getInstance() {
        return proxy;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, new Class[]{Game.class}, this);
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object retVal;
        if (method.getDeclaringClass().isInterface()) {
            method.setAccessible(true);
            retVal = method.invoke(this, args);
        } else {
            retVal = proxy.invokeSuper(obj, args);
        }
        return retVal;
    }

    public void start() {
        System.out.println("start a game");
    }

}
