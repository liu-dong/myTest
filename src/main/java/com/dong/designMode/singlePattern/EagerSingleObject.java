package com.dong.designMode.singlePattern;

/**
 * 饿汉式——单例模式
 *
 * @author LD
 */
public class EagerSingleObject {

    private static EagerSingleObject instance = new EagerSingleObject();

    private EagerSingleObject() {
    }

    public static EagerSingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
