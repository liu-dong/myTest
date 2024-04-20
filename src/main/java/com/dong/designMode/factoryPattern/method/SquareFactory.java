package com.dong.designMode.factoryPattern.method;


public class SquareFactory implements ShapeFactory {

    @Override
    public Shape createShape() {
        return new Square();
    }
}
