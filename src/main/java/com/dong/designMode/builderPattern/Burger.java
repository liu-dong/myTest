package com.dong.designMode.builderPattern;

/**
 * 表示食物中的一种--汉堡 汉堡有名称有价格有包装
 * 表示名称方法不需要重写，因为包装有多种，所以需要重写表示包装的方法，确定为纸装
 * 由于汉堡价格不确实，所以表示价格的方法抽象化
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
