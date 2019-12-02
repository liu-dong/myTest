package com.dong.designMode.singlePattern;

/**
 * 懒汉式——单例模式
 *
 * @author LD
 */
public class LazySingleObject {

    private static LazySingleObject instance = null;

    private LazySingleObject() {
    }

    public static LazySingleObject getInstance() {
        if (null == instance) {
            instance = new LazySingleObject();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
