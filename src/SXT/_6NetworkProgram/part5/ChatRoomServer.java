package SXT._6NetworkProgram.part5;

import java.io.*;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用TCP和多线程实现聊天室的服务端
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 11:18
 **/
public class ChatRoomServer {
    public static void main(String[] args) {
        System.out.println("服务端启动...");
    }

    private static class Channel {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;

        public Channel(Socket client) {
            this.client = client;

            try {
                this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
