package com.dong.javaBase;

import com.dong.javaBase.thread.Hero;
import com.dong.javaBase.thread.KillThread;

public class ThreadTest extends Thread {

    public void run(){
        System.out.println("运行方法");
    }

    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        KillThread killThread1 = new KillThread(gareen, teemo);
        System.out.println("killThread1：" + killThread1.getName());
        killThread1.start();
        KillThread killThread2 = new KillThread(bh, leesin);
        System.out.println("killThread2：" + killThread2.getName());
        killThread2.start();

        /*//盖伦攻击提莫
        while(!teemo.isDead()){
            gareen.attackHero(teemo);
        }

        //赏金猎人攻击盲僧
        while(!leesin.isDead()){
            bh.attackHero(leesin);
        }*/
    }
}
