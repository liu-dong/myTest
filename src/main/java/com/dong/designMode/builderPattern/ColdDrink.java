package com.dong.designMode.builderPattern;

/**
 * 表示冷饮  （同汉堡）
 */
public abstract class ColdDrink implements Item {

    public Packing packing() {
        return new Bottle();
    }

    public abstract float price();

}
