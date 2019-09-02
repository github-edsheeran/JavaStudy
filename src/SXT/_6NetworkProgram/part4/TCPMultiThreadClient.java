package SXT._6NetworkProgram.part4;

import java.io.*;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用多线程和TCP协议实现多用户登录的客户端
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 08:41
 **/
public class TCPMultiThreadClient {
    public static void main(String[] args) {
        System.out.println("客户端启动...");

        try {
            Socket client = new Socket("localhost", 8888);
            // 在这个例子中，虽然实现了多个客户端访问服务端的功能，但是，客户端发送信息之后，必须等待服务端响应之后才能进行下一步
            new Send(client).send();
            new Receive(client).receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Send {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader br;
        private String userName;
        private String password;

        /**
         * 初始化
         * @param client
         */
        public Send(Socket client) {
            this.client = client;

            try {
                this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

            init();
        }

        private void init() {
            try {
                System.out.println("请输入用户名:");
                this.userName = br.readLine();
                System.out.println("请输入密码:");
                this.password = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                this.dos.writeUTF(this.userName);
                this.dos.writeUTF(this.password);
                this.dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Receive {
        private Socket client;
        private DataInputStream dis;

        /**
         * 初始化
         * @param client
         */
        public Receive(Socket client) {
            this.client = client;

            try {
                this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive() {
            try {
                String msg = dis.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
