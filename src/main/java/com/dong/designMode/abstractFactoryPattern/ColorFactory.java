package com.dong.designMode.abstractFactoryPattern;

import com.dong.designMode.factoryPattern.simple.Shape;

public class ColorFactory extends AbstractFactory {

    public Color getColor(String colorType) {
        if (colorType == null)
            return null;
        if (colorType.equalsIgnoreCase("red")){
            return new Red();
        }else if (colorType.equalsIgnoreCase("green")){
            return new Green();
        }else if (colorType.equalsIgnoreCase("blue")){
            return new Blue();
        }
        return null;
    }

    public Shape getShape(String shapeType) {
        return null;
    }
}
