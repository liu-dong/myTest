package com.dong.sortAlgorithm;

/**
 * @author LD
 */
public class SortAlgorithm {


    public static void main(String[] args) {

        int[] x = {6, 2, 4, 1, 5, 9};
        shellSort(x);
        for (int i : x) {
            System.out.print(i + ",");
        }
    }

    //直接插入排序
    public static void insertSort(int[] a) {
        int temp = 0, j;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                temp = a[i];
                j = i;
                while (j > 0 && a[j - 1] > temp) {
                    a[j] = a[j - 1];
                    j--;
                }
                a[j] = temp;
            }
        }
    }

    //希尔排序
    public static void shellSort(int[] a) {
        int d = a.length;
        d = d / 2;
        while (d != 0) {
            for (int x = 0; x < d; x++) {//分的组数
                for (int i = x + d; i < a.length; i += d) {//组中的元素，从第二个数开始
                    int j = i - d;//j为有序序列最后一位的位数
                    int temp = a[i];//要插入的元素
                    for (; j >= 0 && temp < a[j]; j -= d) {//从后往前遍历。
                        a[j + d] = a[j];//向后移动d位
                    }
                    a[j + d] = temp;
                }
            }
        }
    }
}
