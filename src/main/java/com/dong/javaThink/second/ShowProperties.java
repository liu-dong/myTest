package com.dong.javaThink.second;

/**
 *
 *
 * @author liudong 2022/8/12
 */
public class ShowProperties {

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperties().getProperty("user.name"));
        System.out.println(System.getProperties().getProperty("java.library.path"));
    }
}
