package com.dong.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络通信
 *
 * @Author 3hld
 * @Date 2019/12/3 9:02
 * @Version 1.0
 */
public class SocketTest {

    public static void main(String[] args) throws UnknownHostException {
        InternetTest();
    }

    /**
     * InetAddress：此类表示Internet协议（IP）地址
     * @throws UnknownHostException
     */
    private static void InternetTest() throws UnknownHostException {
        //获取本地主机的名称和IP地址
        InetAddress inetAddress = InetAddress.getLocalHost();

        //根据IP地址或主机名称获取主机信息
        InetAddress[] addresses = InetAddress.getAllByName("192.168.4.54");
        InetAddress[] addressArr = InetAddress.getAllByName("3HLIJT");
        System.out.println(inetAddress);
        System.out.println("主机名称：" + addresses[0].getHostName());
        System.out.println("IP地址：" + addressArr[0].getHostAddress());
        System.out.println("全域限定名：" + addressArr[0].getCanonicalHostName());
    }
}
