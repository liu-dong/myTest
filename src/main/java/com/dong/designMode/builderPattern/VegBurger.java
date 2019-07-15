package com.dong.designMode.builderPattern;

/**
 * 表示蔬菜汉堡 汉堡的一种 确定为纸装，所以包装方法不需要重写
 * 确定了具体的名称和价格，
 */
public class VegBurger extends Burger {

    @Override
    public String name() {
        return "蔬菜汉堡";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
