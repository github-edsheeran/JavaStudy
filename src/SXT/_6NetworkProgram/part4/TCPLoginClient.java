package SXT._6NetworkProgram.part4;

import java.io.*;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用TCP协议实现简单的用户登录的客户端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 14:44
 **/
public class TCPLoginClient {
    public static void main(String[] args) {
        System.out.println("客户端启动......");

        DataOutputStream dos = null;
        DataInputStream dis = null;

        try (Socket client = new Socket("localhost", 8888);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("请输入用户名: ");

            String userName = br.readLine();

            System.out.println("请输入密码: ");

            String password = br.readLine();
            dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));

            dos.writeUTF(userName);
            dos.writeUTF(password);
            dos.flush();    // 额外注意

            dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            String msg = dis.readUTF();

            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
