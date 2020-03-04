package com.dong.javaBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 3hld
 * @Date 2020/2/12 9:30
 * @Version 1.0
 */
public class SwitchTest {

    public static void main(String[] args) {
        /*String warn = "其他1";
        switch (9){
            case 8: warn="倾斜Y轴预警";break;
            case 7: warn="倾斜X轴预警";break;
            case 6: warn="超风速预警";break;
            case 5: warn="多机防碰预警";break;
            case 4: warn="禁入区预警";break;
            case 3: warn="超载预警";break;
            case 2: warn="高度限位预警";break;
            case 1: warn="幅度限位预警";break;
            case 0: warn="回转限位预警";break;
            default:warn = "其他";break;
        }
        System.out.println(warn);*/

        listTest();
    }

    public static void listTest(){
        int[] num = {1,2,3,4};
        int[] num1 = {0, 38, 33, 40};
        List list = new ArrayList();
        list.add(num);
        list.add(num1);
        System.out.println(list.toString());
    }
}
