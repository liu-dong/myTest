package com.dong.designMode.decoratorPattern;

import com.dong.designMode.factoryPattern.Shape;

/**
 * 创建实现了 Shape 接口的抽象装饰类
 */
public abstract class ShapeDecorator {

    Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
