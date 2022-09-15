package com.dong.javaThink.twentyfirst;

import java.util.concurrent.TimeUnit;

/**
 * @author liudong 2022/9/15
 */
public class SimpleDaemon implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SimpleDaemon());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All daemon started");
        TimeUnit.MILLISECONDS.sleep(10000);
    }
}
