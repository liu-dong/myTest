package com.dong.designMode.proxyPattern;

/**
 * 代理模式测试类
 * @author 3hld
 */
public class ProxyTest{

    public static void main(String[] args) {
        //目标对象
        MyService myService = new MyServiceImpl();
        /*//代理对象,把目标对象传给代理对象,建立代理关系
        MyService proxyMyService = new ProxyMyServiceImpl(myService);
        //执行代理的方法
        proxyMyService.hello();*/

        // 给目标对象，创建动态代理对象
        MyService proxy = (MyService) new ProxyFactory(myService).getProxyInstance();
        proxy.hello();
        proxy.hi();
    }

}
