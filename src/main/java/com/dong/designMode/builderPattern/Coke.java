package com.dong.designMode.builderPattern;

public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "可口可乐";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
