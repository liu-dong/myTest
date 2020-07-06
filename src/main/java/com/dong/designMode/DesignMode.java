package com.dong.designMode;

import com.dong.designMode.observerPattern.XiaoLi;
import com.dong.designMode.observerPattern.XiaoMei;
import com.dong.designMode.observerPattern.XiaoWang;

public class DesignMode {
    public static void main(String[] args) {

        //工厂模式
        /*
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shapeRectangle = shapeFactory.getShape("rectangle");
        shapeRectangle.draw();
        Shape shapeSquare = shapeFactory.getShape("square");
        shapeSquare.draw();
        Shape shapeCircle = shapeFactory.getShape("circle");
        shapeCircle.draw();
        */

        //抽象工厂模式
        /*
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


        //单例模式
        /*EagerSingleObject object = EagerSingleObject.getInstance();
        object.showMessage();*/


        //建造者模式
        /*
        MealBuilder mealBuilder = new MealBuilder();//实例化一个选择食物套餐类型的对象

        Meal vegMeal = mealBuilder.prepareVegMeal();//选择一个素食套餐
        System.out.println("素食");
        vegMeal.showItems();
        System.out.println("总共花费：" + vegMeal.getCost());

        Meal nonMeal = mealBuilder.prepareNonMeal();//选择一个肉食套餐
        System.out.println("肉食");
        nonMeal.showItems();
        System.out.println("总共花费：" + nonMeal.getCost());
        */

        //适配器模式
        /*AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");*/


        //装饰器模式
        /*
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
//        Shape redCircle = new RedShapeDecorator(new Circle());
//        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("Circle of red border");
        redCircle.draw();

        System.out.println("Rectangle of red border");
        redRectangle.draw();
        */


        //代理模式
        /*Image image = new ProxyImage("dong.gif");
        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        image.display();
        */

        //观察者模式
        XiaoMei xm = new XiaoMei();
        XiaoWang xw = new XiaoWang();
        XiaoLi xl = new XiaoLi();
        xm.attachObserver(xw);
        xm.attachObserver(xl);
        xm.notifyObservers("有人打游戏吗？");

        xm.detachObserver(xw);
        xm.notifyObservers("打完游戏去吃饭吗？");

        //外观模式
//        ShapeMaker shapeMaker = new ShapeMaker();
//
//        shapeMaker.drawRectangle();
//        shapeMaker.drawSquare();
//        shapeMaker.drawCircle();

    }
}
