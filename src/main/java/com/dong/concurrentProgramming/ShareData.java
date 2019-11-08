package com.dong.concurrentProgramming;

public class ShareData {
    public static int count = 0;

    public static void main(String[] args) {
        final ShareData data = new ShareData();
        for (int i = 0; i < 10; i++) {
            //创建线程
            Thread thread = new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         //进入的时候暂停1毫秒，增加并发问题出现的几率
                         Thread.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     for (int j = 0; j < 100; j++) {
                         data.addCount();
                     }
                     System.out.print(":"+count + " ");
                 }
             });
            thread.start();
            System.out.println("线程名称："+thread.getName()+"  状态:"+thread.getState()+"  id:"+thread.getId());
        }
        try {
            //主程序暂停3秒，以保证上面的程序执行完成
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }

    public synchronized void addCount() {
        count++;
    }
}
