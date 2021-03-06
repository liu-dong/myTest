package com.dong.designMode.builderPattern;

/**
 * 表示食物套餐选择  有素食套餐、肉食套餐
 */
public class MealBuilder {

    //素食套餐 不同套餐的组合
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());//给套餐内添加啊食物
        meal.addItem(new Coke());
        return meal;
    }

    //肉食套餐 不同套餐的组合
    public Meal prepareNonMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
