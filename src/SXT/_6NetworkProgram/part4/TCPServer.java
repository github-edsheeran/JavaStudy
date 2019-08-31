package SXT._6NetworkProgram.part4;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现TCP协议中的服务端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 10:41
 **/
public class TCPServer {
    public static void main(String[] args) {
        System.out.println("服务端启动......");

        Socket client = null;

        try {
            // 建立服务端端口
            ServerSocket serverSocket = new ServerSocket(9999);
            // 阻塞式监听
            client = serverSocket.accept();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 一般情况下，服务器会持续运转，很少出现主动关闭的情况
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
