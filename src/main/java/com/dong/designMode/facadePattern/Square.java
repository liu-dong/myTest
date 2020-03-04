package com.dong.designMode.facadePattern;

/**
 * 正方形
 *
 * @author LD
 * @date 2020/3/4 15:24
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个正方形");
    }
}
