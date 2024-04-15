package com.dong.designMode.factoryPattern.method;


import com.dong.designMode.factoryPattern.simple.Circle;
import com.dong.designMode.factoryPattern.simple.Shape;

public class CircleFactory implements ShapeFactory {


    @Override
    public Shape createShape() {
        return new Circle();
    }
}
