package com.dong.designMode.flyweightPattern;

import java.util.HashMap;

/**
 * 享元工厂类
 *
 * @author liudong 2024/4/21
 */
public class ShapeFlyweightFactory {

    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
