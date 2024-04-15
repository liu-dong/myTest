package com.dong.designMode.factoryPattern.simple;

import com.dong.designMode.abstractFactoryPattern.AbstractFactory;
import com.dong.designMode.abstractFactoryPattern.Color;

public class ShapeFactory extends AbstractFactory {

    public Color getColor(String colorType) {
        return null;
    }

    public Shape getShape(String shapeType){
        if (shapeType == null)
            return null;
        if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }else if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        return null;
    }
}
