package com.dong.designMode.prototypePattern;


public class Rectangle extends Shape {

    public Rectangle() {
        type = "长方形";
    }

    public void draw() {
        System.out.println("画了个长方形");
    }
}
