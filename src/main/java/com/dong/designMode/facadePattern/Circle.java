package com.dong.designMode.facadePattern;

/**
 * 圆形
 *
 * @author LD
 * @date 2020/3/4 15:24
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个圆形");
    }
}
