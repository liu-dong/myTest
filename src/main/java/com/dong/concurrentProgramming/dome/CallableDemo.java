package com.dong.concurrentProgramming.dome;

import java.util.concurrent.Callable;

/**
 * 通过实现Callable创建线程
 *
 * @author LD 2021/11/2
 */
public class CallableDemo implements Callable<Integer> {
    private final String threadName;
    private int result;
    private final int num;

    public CallableDemo(String threadName,int num) {
        this.threadName = threadName;
        this.num = num;
        System.out.println("创建线程：" + threadName);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("开始线程：" + threadName);
        Thread.sleep(2000);
        for (int i = 0; i <= num; i++) {
            result = result + i;
        }
        System.out.println("结束线程：" + threadName);
        return result;
    }
}
