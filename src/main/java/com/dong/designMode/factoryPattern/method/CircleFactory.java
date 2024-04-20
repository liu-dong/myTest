package com.dong.designMode.factoryPattern.method;


public class CircleFactory implements ShapeFactory {


    @Override
    public Shape createShape() {
        return new Circle();
    }
}
