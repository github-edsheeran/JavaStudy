package SXT._6NetworkProgram.part5;

import Utils.Utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用TCP和多线程实现聊天室的服务端
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 11:18
 **/
public class ChatRoomServer {
    /**
     * 并发容器CopyOnWriteArrayList，推荐在多线程环境下、有读写需求的时候使用，简单的理解为，对象内部有一个list的副本，每次操作的都是副本，
     * 当list发生变动的时候，马上修改副本里面的内容
     */
    private static List<Channel> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("服务端启动...");

        ServerSocket server = null;

        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                // 监听端口，如果有一个客户端建立了连接，则新开一个线程
                Socket client = server.accept();
                Channel channel = new Channel(client);

                list.add(channel);

                new Thread(channel).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning;
        private String userName;

        /**
         * 初始化
         * @param client
         */
        public Channel(Socket client) {
            this.client = client;

            try {
                this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                this.isRunning = true;
                this.userName = receive();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        /**
         * 服务端接收客户端的相关信息
         */
        private String receive() {
            String msg = "";

            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }

            return msg;
        }

        /**
         * 服务端给客户端返回信息，此时的客户端接收的是自己发送的消息
         */
        private void send(String msg) {
            try {
                this.dos.writeUTF(msg);
                this.dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        /**
         * 群聊
         */
        private void sendOthers(String msg, boolean isSys) {
            for (Channel channel : list) {
                // 遍历所有的客户端，但是消息不发送给自己
                if (channel == this) {
                    continue;
                }

                if (isSys) {
                    channel.send(this.userName + "退出了群聊");
                } else {
                    channel.send(this.userName + "对所有人说: " + msg);
                }
            }
        }

        /**
         * 私聊
         * @param msg
         */
        private void sendSingle(String msg) {
            String userName = msg.substring(msg.indexOf("@") + 1, msg.indexOf(":"));

            for (Channel channel : list) {
                if (userName.equals(channel.getUserName())) {
                    channel.send(this.userName + "对你私聊: " + msg.substring(msg.indexOf(":") + 1));
                    break;
                }
            }
        }

        public String getUserName() {
            return userName;
        }

        @Override
        public void run() {
            while (this.isRunning) {
                String msg = receive();
//                send(this.msg);
                if (!"".equals(msg) && msg.indexOf("@") == -1) {
                    sendOthers(msg, false);
                } else {
                    sendSingle(msg);
                }
            }
        }

        /**
         * 释放资源的同时，相当于退出群聊，从容器中移除
         */
        private void release() {
            this.isRunning = false;
            Utils.release(client, dis, dos);
            list.remove(this);
            sendOthers(null, true);
        }
    }

}
