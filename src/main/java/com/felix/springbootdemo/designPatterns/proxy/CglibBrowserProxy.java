package com.felix.springbootdemo.designPatterns.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib是一种强大的，高性能高品质的代码生成库，用来在运行时扩展JAVA的类以及实现指定接口。
 *
 * 通过cglib提供的Enhancer类的create静态方法来创建代理类
 *
 * Enhancer.create(Class type, Callback callback)
 * type是原对象的Class对象
 * callback是回调方法接口
 * cglib中的callback通过实现它的MethodInterceptor接口的intercept方法
 *
 * public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws Throwable;
 * obj是被代理的对象
 * method是执行的方法
 * args是执行方法的参数数组
 * proxy用来执行未被拦截的原方法
 */

public class CglibBrowserProxy implements MethodInterceptor {

    private static CglibBrowserProxy proxy = new CglibBrowserProxy();

    private CglibBrowserProxy() {

    }

    public static CglibBrowserProxy getInstance() {
        return proxy;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, this);
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        encrypt();
        Object retVal = proxy.invokeSuper(obj, args);
        decrypt();
        return retVal;
    }

    /**
     * 加密
     */
    private void encrypt() {
        System.out.println("encrypt ...");
    }

    /**
     * 解密
     */
    private void decrypt() {
        System.out.println("decrypt ...");
    }
}
