package com.dong.socket.many;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author 3hld
 * @Date 2019/12/3 16:47
 * @Version 1.0
 */
public class SocketHandler implements Runnable {

    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取socket里的输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取socket里的输出流
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //获取键盘的输入流
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            boolean flag = true;
            while (flag) {
                if (reader.ready()) {
                    String readInfo = reader.readLine();
                    System.out.println("客户端：" + readInfo);
                    //如果对方输入BYE，关闭回话
                    if ("BYE".equalsIgnoreCase(readInfo)) {
                        socket.close();
                        flag = false;
                    }
                }
                if (input.ready()) {
                    String writeInfo = input.readLine();
                    writer.println(writeInfo);
                    System.out.println("服务端：" + writeInfo);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
