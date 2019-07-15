package com.dong.designMode.builderPattern;

/**
 * 表示鸡肉汉堡 汉堡的一种 确定为纸装，所以包装方法不需要重写
 * 确定了具体的名称和价格，
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
