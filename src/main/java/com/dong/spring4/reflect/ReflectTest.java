package com.dong.spring4.reflect;

import com.dong.javaBase.clone.MyUtils;
import com.dong.spring4.serializable.Car;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 反射
 * @author 3hld
 */
public class ReflectTest {

    public static com.dong.spring4.serializable.Car initByDefaultConst() throws Throwable {
        //通过类装载器获取对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.dong.spring4.reflect.Car");

        //获取类的默认构造器对象，并通过它实例化类
//        Constructor constructor = clazz.getDeclaredConstructor((Class<?>[]) null);
//        Car car = (Car) constructor.newInstance();
        Constructor constructor = clazz.getConstructor();
        com.dong.spring4.serializable.Car car = (com.dong.spring4.serializable.Car) constructor.newInstance();

        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand",String.class);
        setBrand.invoke(car,"红旗CA72");
        Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car,"黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car,200);
//        Object clone = (Object) car.clone();
        com.dong.spring4.serializable.Car clone1 = MyUtils.clone(car);
//        System.out.println("1："+clone.toString());
        System.out.println("2："+car.toString());
        System.out.println("3："+clone1.toString());

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
//        car.introduce();

//        Car car1 = new Car();
//        car1 = new Car("红旗CA72","黑色",200);
//        car1.introduce();
    }
}
