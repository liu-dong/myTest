package com.dong.spring4.serializable;

import java.io.*;

/**
 * 序列化
 * @Author 3hld
 * @Date 2019/11/28 15:11
 * @Version 1.0
 */
public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializableMethod();
        Car car = deserializationMethod();
        System.out.println(car.toString());
    }

    /**
     * 序列化
     */
    private static void serializableMethod() throws IOException {
        Car car = new Car();
        car.setBrand("BWM");
        car.setColor("黑色");
        car.setMaxSpeed(1314520);
        // ObjectOutputStream 对象输出流，将对象存储到磁盘文件中，完成对 car 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("G:/carSerializable.txt")));
        oos.writeObject(car);
        System.out.println("对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static Car deserializationMethod() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("G:/carSerializable.txt")));
        Car car = (Car) ois.readObject();
        System.out.println("对象反序列化成功！");
        return car;
    }
}
