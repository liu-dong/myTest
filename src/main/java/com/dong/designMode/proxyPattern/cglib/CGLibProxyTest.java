package com.dong.designMode.proxyPattern.cglib;

/**
 * CGLib动态代理测试类
 *
 * @Author 3hld
 * @Date 2019/12/5 11:19
 * @Version 1.0
 */
public class CGLibProxyTest {

    public static void main(String[] args) {
        // 实例一个代理类
        CGLibProxy proxy = new CGLibProxy();
        // 代理类通过动态生成子类的方式创建代理的类
        MyServiceImpl myService = (MyServiceImpl) proxy.getProxy(MyServiceImpl.class);
        myService.hello();
        myService.hi();

    }
}
