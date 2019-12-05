package com.dong.designMode.proxyPattern.jdk;

/**
 * 代理模式测试类(静态代理)
 * @author 3hld
 */
public class ProxyMyServiceImpl implements MyService{

   private MyService myService;

    @Override
    public void hello() {
        System.out.println("打招呼开始");
        myService.hello();
        System.out.println("打招呼结束");
    }

    @Override
    public void hi() {
        System.out.println("打招呼开始");
        myService.hi();
        System.out.println("打招呼结束");
    }

    public ProxyMyServiceImpl(MyService myService){
        this.myService=myService;
    }

}
