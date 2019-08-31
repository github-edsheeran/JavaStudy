package SXT._6NetworkProgram.part4;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现TCP协议中的客户端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 11:04
 **/
public class TCPClient {
    public static void main(String[] args) {
        System.out.println("客户端启动......");

        // 自动释放客户端的资源
        try (Socket socket = new Socket("localhost", 9999);
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
            dos.writeUTF("Hello, world!");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
