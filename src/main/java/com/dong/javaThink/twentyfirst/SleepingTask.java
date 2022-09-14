package com.dong.javaThink.twentyfirst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liudong 2022/9/14
 */
public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            try {
                //Thread.sleep(100);
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.execute(new SleepingTask());
        }
        service.shutdown();
    }
}
