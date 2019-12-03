package com.dong.socket.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket通信客户端
 *
 * @Author 3hld
 * @Date 2019/12/3 11:50
 * @Version 1.0
 */
public class SocketClientTest {

    public static void main(String[] args) throws IOException {
        //创建一个Socket
        Socket socket = new Socket();
        //创建一个Socket地址
        InetSocketAddress address = new InetSocketAddress("localhost",18824);
        //连接Socket
        socket.connect(address);

        /*
            BufferedReader：从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。
            InputStreamReader：是从字节流到字符流的桥
            socket.getInputStream()：返回的是字节流
        */
        //获取socket里的输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //获取socket里的输出流
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        //获取键盘的输入流
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //循环出字符流里的字符
        while (true) {
            if (reader.ready()) {
                String readInfo = reader.readLine();
                System.out.println("服务端：" + readInfo);
            }
            if (input.ready()) {
                String writeInfo = input.readLine();
                writer.println(writeInfo);
                System.out.println("客户端：" + writeInfo);

            }
        }
    }
}
