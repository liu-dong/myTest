package com.dong.spring4.serializable.rmi;

import com.dong.spring4.serializable.Car;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 实现远程服务对象，必须继承UnicastRemoteObject或其子类
 * @Author 3hld
 * @Date 2019/11/28 16:25
 * @Version 1.0
 */
public class HelloImpl extends UnicastRemoteObject implements Hello {

    private static final long serialVersionUID = -6186700587851681006L;

    protected HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(Car car) throws RemoteException {
        System.out.println("this is server, hello:" + car.getBrand());
        return "hello";
    }
}
