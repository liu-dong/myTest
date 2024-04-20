package com.dong.designMode.factoryPattern.method;

/**
 * @author liudong 2024/4/15
 */
public class Main {

    public static void main(String[] args) {
        ShapeClient client = new ShapeClient(new RectangleFactory());
        Shape shape = client.create();
        shape.draw();
    }

}
