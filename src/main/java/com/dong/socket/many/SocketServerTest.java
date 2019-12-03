package com.dong.socket.many;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket通信服务端
 *
 * @Author 3hld
 * @Date 2019/12/3 11:39
 * @Version 1.0
 */
public class SocketServerTest {

    public static void main(String[] args) throws IOException {

        //创建一个ServerSocket
        ServerSocket server = new ServerSocket();
        //创建一个套接字地址
        InetSocketAddress address = new InetSocketAddress("localhost", 18824);
        //绑定监听指定地址和端口号
        server.bind(address);

        while (true) {
            // 循环调用accept()方法，返回相应的Socket
            Socket socket = server.accept();
            //使用线程，将每一个Socket都封装到线程内，这个每个接受的Socket可以自由的跟服务器通信了
            new Thread(new SocketHandler(socket)).start();
        }


    }
}
