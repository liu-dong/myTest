package com.dong.javaThink;

/**
 * @author liudong 2022/7/21
 */
public class Test {

    public static void main(String[] args) {
        int i = 0;
        outer:
        while (true) {
            System.out.println("outer start ");
            while (true) {
                i++;
                if (i == 1) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("break outer");
                    break outer;
                }
            }
        }
    }
}
