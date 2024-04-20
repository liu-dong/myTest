package com.dong.designMode.factoryPattern.method;


public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape createShape() {
        return new Rectangle();
    }

}
