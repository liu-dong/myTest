package com.dong.designMode.builderPattern;

/**
 *表示食物 食物有名称、有包装、有价格
 */
public interface Item {
    String name();
    Packing packing();
    float price();
}
