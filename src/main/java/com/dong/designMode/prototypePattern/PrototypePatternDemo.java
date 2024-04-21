package com.dong.designMode.prototypePattern;

/**
 * @author liudong 2024/4/20
 */
public class PrototypePatternDemo {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());
        Shape clonedShape11 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape11.getType());

        System.out.println(clonedShape1);
        System.out.println(clonedShape11);
        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
