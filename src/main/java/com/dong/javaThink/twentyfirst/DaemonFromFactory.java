package com.dong.javaThink.twentyfirst;

import com.mongodb.internal.thread.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liudong 2022/9/15
 */
public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        executorService.execute(new DaemonFromFactory());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new DaemonFromFactory());
        }
        System.out.println("All daemon started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
