package com.dong.designMode.factoryPattern.method;


import com.dong.designMode.factoryPattern.simple.Shape;

/**
 * @author liudong 2024/4/15
 */
public class ShapeClient {

    private final ShapeFactory factory;

    public ShapeClient(ShapeFactory factory) {
        this.factory = factory;
    }

    public Shape create() {
        return factory.createShape();
    }


}
