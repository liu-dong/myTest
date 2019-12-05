package com.dong.designMode.proxyPattern.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib动态代理
 *
 * @Author 3hld
 * @Date 2019/12/5 11:11
 * @Version 1.0
 */
public class CGLibProxy implements MethodInterceptor {

    /**
     * 允许为非接口类型创建一个Java代理
     * Enhancer动态创建了给定类型的子类但是拦截了所有的方法
     */
    private Enhancer enhancer = new Enhancer();

    /**
     * 为一个类创建动态代理对象
     * @param clazz
     * @return
     */
    public Object getProxy(Class clazz) {
        // 设置代理目标
        enhancer.setSuperclass(clazz);
        // 设置回调对象，在调用中拦截对目标方法的调用
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("打招呼开始");
        Object result = methodProxy.invokeSuper(obj, objects);
        System.out.println("打招呼结束");
        return result;
    }
}
