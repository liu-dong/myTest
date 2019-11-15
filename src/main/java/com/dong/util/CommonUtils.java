package com.dong.util;

import java.util.Random;

public class CommonUtils {

    public static void main(String[] args) {
        getRandomNumber();


    }

    public static void getRandomNumber(){
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < 10000000; i++) {
            int num = random.nextInt(99);
            if (num > 80 && num < 98){
                System.out.println(num);
                count++;
            }
            if (count > 15){
                return;
            }
        }
        int num = (int) (Math.random()*10);
    }
}
