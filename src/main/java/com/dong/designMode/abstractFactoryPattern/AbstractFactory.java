package com.dong.designMode.abstractFactoryPattern;

import com.dong.designMode.factoryPattern.simple.Shape;

public abstract class AbstractFactory {

    public abstract Color getColor(String colorType);

    public abstract Shape getShape(String shapeType);
}
