package com.dong.designMode.builderPattern;

public abstract class ColdDrink implements Item {

    public Packing packing() {
        return new Bottle();
    }

    public abstract float price();

}
