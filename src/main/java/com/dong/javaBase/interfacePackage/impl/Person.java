package com.dong.javaBase.interfacePackage.impl;

import com.dong.javaBase.interfacePackage.HumanService;

/**
 * @author LD 2021/12/24
 */
public class Person implements HumanService {

    @Override
    public void speak() {
        System.out.println("我会说话");
    }

    @Override
    public void drink() {
        System.out.println("我会喝酒");
    }

    @Override
    public void smoke() {
        System.out.println("我会吸烟");
    }
}
