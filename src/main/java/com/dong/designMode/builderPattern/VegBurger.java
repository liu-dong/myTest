package com.dong.designMode.builderPattern;

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
