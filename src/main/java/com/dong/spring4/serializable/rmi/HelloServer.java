package com.dong.spring4.serializable.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 服务端程序
 * @Author 3hld
 * @Date 2019/11/28 16:27
 * @Version 1.0
 */
public class HelloServer {

    public static void main(String[] args) {
        try {
            // 创建一个远程对象，同时也会创建stub对象（存根，用于客户端）、skeleton对象（骨架，用于服务器端）
            Hello hello = new HelloImpl();
            //启动注册服务
            LocateRegistry.createRegistry(8888);
            try {
                //将stub引用绑定到服务地址上
                Naming.bind("//localhost:8888/wbh",hello);
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            System.out.println("绑定成功！");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
