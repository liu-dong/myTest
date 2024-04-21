package com.dong.designMode.prototypePattern;


public class Square extends Shape {

    public Square() {
        type = "正方形";
    }


    public void draw() {
        System.out.println("画了个正方形");
    }
}
