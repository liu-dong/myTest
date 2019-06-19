package com.dong.designMode;

public class SingleObject {

    private static SingleObject instance = new SingleObject();

    private SingleObject(){}

//    public SingleObject(String type){
//        System.out.println("Hello World!" + type);
//    }

    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
