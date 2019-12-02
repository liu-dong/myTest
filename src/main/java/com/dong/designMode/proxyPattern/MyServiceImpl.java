package com.dong.designMode.proxyPattern;

public class MyServiceImpl implements MyService {

    @Override
    public void hi() {
        System.out.println("hi! world");
    }

    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
