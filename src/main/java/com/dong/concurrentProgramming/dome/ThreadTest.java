package com.dong.concurrentProgramming.dome;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author LD 2021/11/2
 */
public class ThreadTest {

    public static void main(String[] args) {
        //RunnableDemo runnableDemo1 = new RunnableDemo("线程-1");
        //runnableDemo1.start();
        //RunnableDemo runnableDemo2 = new RunnableDemo("线程-2");
        //runnableDemo2.start();

        //ThreadDemo runnableDemo1 = new ThreadDemo("线程-1");
        //runnableDemo1.start();
        //ThreadDemo runnableDemo2 = new ThreadDemo("线程-2");
        //runnableDemo2.start();

        //List<Integer> result = new ArrayList<>();
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //List<CallableDemo> callableDemoList = new ArrayList<>();
        //CallableDemo callableDemo1 = new CallableDemo("线程-1", 10);
        //CallableDemo callableDemo2 = new CallableDemo("线程-2", 9);
        //callableDemoList.add(callableDemo1);
        //callableDemoList.add(callableDemo2);
        //try {
        //    List<Future<Integer>> tasks = executorService.invokeAll(callableDemoList);
        //    try {
        //        Thread.sleep(2000);
        //        System.out.println(Thread.currentThread().getName() + "线程执行任务");
        //        try {
        //            if (CollectionUtils.isNotEmpty(tasks)) {
        //                for (Future<Integer> task : tasks) {
        //                    result.add(task.get());
        //                    System.out.println("子线程执行结果：" + task.get());
        //                }
        //            } else {
        //                System.out.println("子线程执行未有结果");
        //            }
        //        } catch (ExecutionException e) {
        //            System.out.println("子线程执行错误");
        //            e.printStackTrace();
        //        }
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //        System.out.println("主线程执行错误！");
        //    }
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //
        //System.out.println("主线程执行完成：" + result.toString());

        List<Integer> result = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        CallableDemo callableDemo1 = new CallableDemo("线程-1", 10);
        CallableDemo callableDemo2 = new CallableDemo("线程-2", 9);
        FutureTask<Integer> task1 = new FutureTask<>(callableDemo1);
        FutureTask<Integer> task2 = new FutureTask<>(callableDemo2);
        taskList.add(task1);
        taskList.add(task2);
        executorService.submit(task1);
        executorService.submit(task2);
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "线程执行任务");
            try {
                if (CollectionUtils.isNotEmpty(taskList)) {
                    for (Future<Integer> task : taskList) {
                        result.add(task.get());
                        System.out.println("子线程执行结果：" + task.get());
                    }
                } else {
                    System.out.println("子线程执行未有结果");
                }
            } catch (ExecutionException e) {
                System.out.println("子线程执行错误");
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("主线程执行错误！");
        }

        System.out.println("主线程执行完成：" + result.toString());

    }
}
