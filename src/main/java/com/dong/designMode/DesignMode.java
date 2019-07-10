package com.dong.designMode;

import com.dong.designMode.builderPattern.Meal;
import com.dong.designMode.builderPattern.MealBuilder;

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

        //建造者模式
        MealBuilder mealBuilder = new MealBuilder();//实例化一个选择食物套餐类型的对象

        Meal vegMeal = mealBuilder.prepareVegMeal();//选择一个素食套餐
        System.out.println("素食");
        vegMeal.showItems();
        System.out.println("总共花费：" + vegMeal.getCost());

        Meal nonMeal = mealBuilder.prepareNonMeal();//选择一个肉食套餐
        System.out.println("肉食");
        nonMeal.showItems();
        System.out.println("总共花费：" + nonMeal.getCost());

    }
}
