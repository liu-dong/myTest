package com.dong.designMode.builderPattern;

/**
 * 构建一个食物套餐
 */
public class MealBuilder {

    //素食套餐
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());//给套餐内添加啊食物
        meal.addItem(new Coke());
        return meal;
    }

    //肉食套餐
    public Meal prepareNonMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
