package com.dong.designMode.facadePattern;

/**
 * 长方形
 *
 * @author LD
 * @date 2020/3/4 15:23
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个长方形");
    }
}
