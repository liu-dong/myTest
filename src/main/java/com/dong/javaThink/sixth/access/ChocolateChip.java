package com.dong.javaThink.sixth.access;

import com.dong.javaThink.sixth.access.dessert.Cookie;

/**
 * @author liudong 2022/8/17
 */
public class ChocolateChip extends Cookie {

    public void chomp(){
        bite();
    }

    public ChocolateChip() {
        System.out.println("ChocolateChip is constructor");
    }

    public static void main(String[] args) {
        ChocolateChip x = new ChocolateChip();
        x.chomp();
    }
}
