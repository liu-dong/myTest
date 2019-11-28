package com.dong.spring4.serializable.rmi;

import com.dong.spring4.serializable.Car;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程服务对象
 * @author 3hld
 */
public interface Hello extends Remote {

    String sayHello(Car car) throws RemoteException;
}
