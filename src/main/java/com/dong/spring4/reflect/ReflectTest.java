package com.dong.spring4.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectTest {

    public static Car initByDefaultConst() throws Throwable {
        //通过类装载器获取对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.dong.spring4.reflect.Car");

        //获取类的默认构造器对象，并通过它实例化类
//        Constructor constructor = clazz.getDeclaredConstructor((Class<?>[]) null);
//        Car car = (Car) constructor.newInstance();
        Constructor constructor = clazz.getConstructor();
        Car car = (Car) constructor.newInstance();

        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand",String.class);
        setBrand.invoke(car,"红旗CA72");
        Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car,"黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car,200);

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();

//        Car car1 = new Car();
//        car1 = new Car("红旗CA72","黑色",200);
//        car1.introduce();
    }
}
