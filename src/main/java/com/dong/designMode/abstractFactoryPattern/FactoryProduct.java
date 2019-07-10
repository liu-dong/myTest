package com.dong.designMode.abstractFactoryPattern;

import com.dong.designMode.factoryPattern.ShapeFactory;

public class FactoryProduct {

    public static AbstractFactory getFactory(String factoryType){
        if (factoryType.equalsIgnoreCase("shape")){
            return new ShapeFactory();
        }else if(factoryType.equalsIgnoreCase("color")){
            return new ColorFactory();
        }
        return null;
    }
}
