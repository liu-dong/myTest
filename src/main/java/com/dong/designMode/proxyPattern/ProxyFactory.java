package com.dong.designMode.proxyPattern;

import java.lang.reflect.Proxy;

/**
 * 代理工厂（动态代理）
 * 创建动态代理对象 动态代理不需要实现接口,但是需要指定接口类型
 *
 * @Author 3hld
 * @Date 2019/11/29 10:30
 * @Version 1.0
 */
public class ProxyFactory {
    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("打招呼开始");
                        Object returnValue = method.invoke(target, args);
                        System.out.println("打招呼结束");
                        return returnValue;
                    }
        );
    }
}
