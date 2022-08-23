package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/23
 */
public class Apply1 {
    public static void process(Processor1 p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }
}
