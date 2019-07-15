package com.dong.designMode.builderPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个套餐 套餐里会有多种食物
 */
public class Meal {

    private List<Item> items = new ArrayList<Item>();

    //添加套餐的组成
    void addItem(Item item){
        items.add(item);
    }

    //计算套餐的花费
    public float getCost(){
        float cost = 0.0f;
        for (Item item : items){
            cost += item.price();
        }
        return cost;
    }

    //显示套餐的信息
    public void showItems(){
        for (Item item : items){
            System.out.print("套餐组成：" + item.name());
            System.out.print(",包装：" + item.packing().pack());
            System.out.println(",价格：" + item.price());
        }
    }
}
