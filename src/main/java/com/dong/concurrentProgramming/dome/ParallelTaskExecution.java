package com.dong.concurrentProgramming.dome;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTaskExecution {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 CountDownLatch 用于等待任务 A 和 B 完成
        CountDownLatch latch = new CountDownLatch(2);

        // 创建一个 ExecutorService 用于并发执行任务
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 提交任务 A
        executorService.submit(() -> {
            try {
                // 执行任务 A 的代码
                System.out.println("Task A is running.");
                // 模拟执行时间
                Thread.sleep(1000);
                System.out.println("Task A completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // 完成后计数器减一
                latch.countDown();
            }
        });

        // 提交任务 B
        executorService.submit(() -> {
            try {
                // 执行任务 B 的代码
                System.out.println("Task B is running.");
                // 模拟执行时间
                Thread.sleep(1200);
                System.out.println("Task B completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // 完成后计数器减一
                latch.countDown();
            }
        });

        // 等待任务 A 和 B 完成
        latch.await();

        // 执行任务 C
        System.out.println("Task C is running after Task A and B completed.");

        // 模拟任务 C 的执行
        Thread.sleep(800);
        System.out.println("Task C completed.");

        // 关闭 ExecutorService
        executorService.shutdown();
    }
}
