package com.dong.designMode.flyweightPattern;

public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("画了个圆形 [Color : " + color
                + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
