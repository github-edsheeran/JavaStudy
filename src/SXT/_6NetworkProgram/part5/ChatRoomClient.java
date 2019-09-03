package SXT._6NetworkProgram.part5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用TCP和多线程实现聊天室的客户端
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 11:17
 **/
public class ChatRoomClient {
    public static void main(String[] args) {
        System.out.println("客户端启动......");

        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        Socket client = null;

        System.out.println("请输入你的用户名:");

        try {
            String userName = br.readLine();
            client = new Socket("localhost", 8888);

            // 每个客户端都有着两个线程，一个线程一直等待给服务端发送消息，另一个线程一直等待服务端返回的消息
            new Thread(new ClientSend(client, userName)).start();
            new Thread(new ClientReceive(client)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
