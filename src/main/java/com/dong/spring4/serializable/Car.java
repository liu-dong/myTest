package com.dong.spring4.serializable;

import java.io.Serializable;

/**
 * @author 3hld
 */
public class Car implements Serializable {

    private static final long serialVersionUID = -5578085472282035046L;
    private String brand;
    /**
     * transient 修饰的属性，是不会被序列化的
     * 静态static修饰的属性，也不序列化
     */
    transient private String color;
    private int maxSpeed;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}