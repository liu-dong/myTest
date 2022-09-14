package com.dong.javaThink.twentyfirst;

/**
 * @author liudong 2022/9/14
 */
public class BasicThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff");
    }
}
