package com.dong.javaThink.twentyfirst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liudong 2022/9/14
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 7; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
