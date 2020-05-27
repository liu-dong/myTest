package com.dong.leetcode;

import org.w3c.dom.Node;

/**
 * @author LD
 * @date 2020/5/20 11:00
 */
public class 两数之和 {
    public static int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = {2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        Node text;
        System.out.println(result.toString());
    }
}
