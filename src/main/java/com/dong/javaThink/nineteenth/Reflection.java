package com.dong.javaThink.nineteenth;

import java.util.Set;

/**
 * @author liudong
 * @date 2023/6/1
 */
enum Explore {HEER, THERE}

public class Reflection {

    public void analyze(Class<?> enumClass){

    }

    public static void main(String[] args) {

        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("------------Analyzing " + e);
    }
}
