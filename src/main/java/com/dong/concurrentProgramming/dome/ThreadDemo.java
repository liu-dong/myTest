package com.dong.concurrentProgramming.dome;

/**
 * 继承Thread方式创建线程
 *
 * @author LD 2021/11/2
 */
public class ThreadDemo extends Thread {

    private Thread thread;
    private String threadName;

    public ThreadDemo(String name) {
        threadName = name;
        System.out.println("创建线程：" + threadName);
    }


    @Override
    public void run() {
        System.out.println("运行线程：" + threadName);
        for (int i = 0; i < 4; i++) {
            try {
                System.out.println(threadName+"：执行任务-"+i);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("线程中断：" + threadName);
                e.printStackTrace();
            }
        }
        System.out.println("线程退出：" + threadName);
    }

    public void start(){
        System.out.println("开始线程：" + threadName);
        if (thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
