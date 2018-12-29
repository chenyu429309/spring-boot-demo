package com.felix.springbootdemo.designPatterns.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在JDK中提供了一种代理的实现方式，可以动态地创建代理类，就是java.lang.reflect包中的Proxy类提供的newProxyInstance方法。
 *
 * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 * classLoader是创建代理类的类加载器
 * interfaces是原对象实现的接口
 * InvocationHandler是回调方法的接口
 * 真正的代理过程通过InvocationHandler接口中的invoke方法来实现
 *
 * public Object invoke(Object proxy, Method method, Object[] args)
 * throws Throwable;
 * proxy是代理对象
 * method是执行的方法
 * args是执行方法的参数数组
 */
public class JdkBrowserProxy implements InvocationHandler {

    private Browser browser;

    public JdkBrowserProxy(Browser browser) {
        this.browser = browser;
    }

    public Browser getProxy() {
        return (Browser) Proxy.newProxyInstance(browser.getClass().getClassLoader(),
                browser.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        encrypt();
        Object retVal = method.invoke(browser, args);
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
