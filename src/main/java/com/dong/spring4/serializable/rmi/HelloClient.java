package com.dong.spring4.serializable.rmi;

import com.dong.spring4.serializable.Car;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 客户端程序
 * @Author 3hld
 * @Date 2019/11/28 16:29
 * @Version 1.0
 */
public class HelloClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        //获取远程对象
        Hello hello = (Hello) Naming.lookup("//localhost:8888/wbh");
        Car car = new Car();
        car.setBrand("BWM");
        car.setColor("白色");
        car.setMaxSpeed(520);
        System.out.println(hello.sayHello(car));

    }
}
