package com.dong.designMode.factoryPattern.method;


import com.dong.designMode.factoryPattern.simple.Rectangle;
import com.dong.designMode.factoryPattern.simple.Shape;

public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape createShape() {
        return new Rectangle();
    }

}
