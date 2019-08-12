package com.dong.javaBase;

/**
 * 三种代码方式的执行顺序
 */
public class HelloA {

    int i = 0;

    public HelloA() {
        System.out.println("I'm A construct method code block:"+(i++));
    }

    {
        System.out.println("I'm A construct code block"+(i++));
    }

    static {
        System.out.println("I'm A static code block");
    }

    public static void main(String[] args) {
        new HelloA();
        new HelloA();
    }
}
