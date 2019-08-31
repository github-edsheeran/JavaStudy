package SXT._6NetworkProgram.part4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用TCP协议实现简单的用户登录的服务端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 14:45
 **/
public class TCPLoginServer {
    public static void main(String[] args) {
        System.out.println("服务端启动......");

        DataInputStream dis = null;
        Socket client = null;
        DataOutputStream dos = null;

        try {
            ServerSocket server = new ServerSocket(8888);
            client = server.accept();
            dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            String userName = dis.readUTF();
            String password = dis.readUTF();
            dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            if ("liudongman".equals(userName) && "123456".equals(password)) {
                dos.writeUTF("登录成功!");
            } else {
                dos.writeUTF("登录失败!");
            }

            dos.flush();    // 额外注意
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
