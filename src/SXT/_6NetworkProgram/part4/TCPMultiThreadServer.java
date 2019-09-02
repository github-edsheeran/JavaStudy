package SXT._6NetworkProgram.part4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用多线程和TCP协议实现多用户登录的服务端
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 08:41
 **/
public class TCPMultiThreadServer {
    public static void main(String[] args) {
        System.out.println("服务端启动...");

        try {
            ServerSocket server = new ServerSocket(8888);

            while (true) {
                // 监听端口，如果有一个客户端建立了连接，则新开一个线程
                Socket client = server.accept();
                System.out.println("一个客户端建立了连接......");
                new Thread(new Channel(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将客户端和服务端的连接封装成一个类
     */
    private static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private String userName;
        private String password;

        /**
         * 初始化
         * @param client
         */
        public Channel(Socket client) {
            this.client = client;

            try {
                this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            receive();
            send();
        }

        /**
         * 服务端接收客户端信息
         */
        private void receive() {
            try {
                this.userName = dis.readUTF();
                this.password = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 服务端给客户端返回信息
         */
        public void send() {
            String msg = null;

            if ("liudongman".equals(this.userName) && "123456".equals(this.password)) {
                msg = "登录成功!";
            } else {
                msg = "登录失败, 账号或密码错误!";
            }

            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
