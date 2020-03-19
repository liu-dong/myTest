package com.dong.javaBase.recursion;

/**
 * @author LD
 * @date 2020/2/7 11:53
 */
public class RecursionTest {

    static int result = 0;

    public static void main(String[] args) {
        recursionSum(10);
        result = 0;
        recursionSum(100);
        result = 0;
        recursionSum(1000);
//        result = 0;
//        recursionSum(10000);
//        recursionSum(100000);
//        recursionSum(1000000);
//        recursionSum(10000000);
//        recursionSum(100000000);
//        recursionSum(1000000000);
    }

    /**
     * 递归求和
     *
     * @param num
     */
    public static void recursionSum(int num) {
        if (0 == num) {
            System.out.println("result："+result);
        } else {
            result += num;
            recursionSum(--num);
        }
    }
}
