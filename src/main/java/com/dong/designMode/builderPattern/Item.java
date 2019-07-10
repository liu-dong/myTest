package com.dong.designMode.builderPattern;

/**
 *表示食物条目
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
