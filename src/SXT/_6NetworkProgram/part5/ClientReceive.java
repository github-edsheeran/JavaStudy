package SXT._6NetworkProgram.part5;

import Utils.Utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装客户端中的接收功能，并实现Runnable接口
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 11:20
 **/
public class ClientReceive implements Runnable {
    private Socket client;
    private DataInputStream dis;
    private boolean isRunning;

    public ClientReceive(Socket client) {
        this.client = client;
        this.isRunning = true;

        try {
            this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    /**
     * 客户端接收服务端信息
     */
    @Override
    public void run() {
        while (this.isRunning) {
            String msg = receive();

            if (!"".equals(msg)) {
                System.out.println(msg);
            }
        }
    }

    /**
     * 接收消息
     * @return
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
     * 释放资源
     */
    private void release() {
        this.isRunning = false;
        Utils.release(client, dis);
    }
}
