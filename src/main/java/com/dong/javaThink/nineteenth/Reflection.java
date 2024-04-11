package com.dong.javaThink.nineteenth;

import java.util.Set;

/**
 * @author liudong
 * @date 2023/6/1
 */
enum Explore {HEER, THERE}

public class Reflection {

    public static Set<String> analyze(Class<?> enumClass){

        return null;
    }

    public static void main(String[] args) {

        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
    }
}
