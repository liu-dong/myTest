package com.dong.designMode.decoratorPattern;

import com.dong.designMode.factoryPattern.Shape;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);//调用父类有参构造方法
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("边框颜色: 红色");
    }
}
