package com.dong.designMode.prototypePattern;


public class Circle extends Shape {

    public Circle() {
        type = "三角形";
    }

    public void draw() {
        System.out.println("画了个三角形");
    }
}
