package com.dong.socket.one;

import java.io.*;
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
        //接受绑定端口的所有通信请求
        Socket socket = server.accept();

        /*
            BufferedReader：从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。
            InputStreamReader：是从字节流到字符流的桥
            socket.getInputStream()：返回的是字节流
        */
        //获取socket里的输入字节流
        InputStream inputStream = socket.getInputStream();
        //字节流转换为字符流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //字符流添加缓冲
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //获取socket里的输出流
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        //获取键盘的输入流
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //循环出字符流里的字符
        while (true) {
            if (reader.ready()) {
                String readInfo = reader.readLine();
                System.out.println("客户端：" + readInfo);
            }
            if (input.ready()) {
                String writeInfo = input.readLine();
                writer.println(writeInfo);
                System.out.println("服务端：" + writeInfo);

            }
        }


    }
}
