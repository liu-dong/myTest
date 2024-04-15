package com.dong.designMode.factoryPattern.method;


import com.dong.designMode.factoryPattern.simple.Shape;
import com.dong.designMode.factoryPattern.simple.Square;

public class SquareFactory implements ShapeFactory {

    @Override
    public Shape createShape() {
        return new Square();
    }
}
