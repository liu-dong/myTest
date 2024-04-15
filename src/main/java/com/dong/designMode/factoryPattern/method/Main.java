package com.dong.designMode.factoryPattern.method;

import com.dong.designMode.factoryPattern.simple.Shape;

/**
 * @author liudong 2024/4/15
 */
public class Main {

    private static ShapeFactory factory;

    public Main(ShapeFactory factory) {
        this.factory = factory;
    }


    public static void main(String[] args) {
        ShapeFactory shapeFactory = new SquareFactory();
        Shape shape = create();
    }

    public static Shape create() {
        return factory.createShape();
    }

}
