package com.dong.designMode;

public class DesignMode {
    public static void main(String[] args) {

        /*
        //工厂模式
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shapeRectangle = shapeFactory.getShape("rectangle");
        shapeRectangle.draw();
        Shape shapeSquare = shapeFactory.getShape("square");
        shapeSquare.draw();
        Shape shapeCircle = shapeFactory.getShape("circle");
        shapeCircle.draw();
        */

        /*
        //抽象工厂模式
        AbstractFactory shapeFactory = FactoryProduct.getFactory("shape");
        Shape shapeRectangle = shapeFactory.getShape("rectangle");
        Shape shapeSquare = shapeFactory.getShape("square");
        Shape shapeCircle = shapeFactory.getShape("circle");
        AbstractFactory colorFactory = FactoryProduct.getFactory("color");
        Color colorRed = colorFactory.getColor("red");
        Color colorGreen = colorFactory.getColor("green");
        Color colorBlue = colorFactory.getColor("blue");
        shapeRectangle.draw();
        colorRed.fill();
        shapeSquare.draw();
        colorGreen.fill();
        shapeCircle.draw();
        colorBlue.fill();
        */

        /*
        //单例模式
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
        */

    }
}
