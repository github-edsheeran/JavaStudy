package SXT._6NetworkProgram.part5;

import Utils.Utils;

import java.io.*;
import java.net.Socket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装客户端中的发送功能，并实现Runnable接口
 * @author: LiuDongMan
 * @createdDate: 2019-09-02 11:19
 **/
public class ClientSend implements Runnable {
    private Socket client;
    private DataOutputStream dos;
    private BufferedReader br;
    private boolean isRunning;
    private String userName;

    /**
     * 成员变量的初始化最好是放在构造器中
     * @param client
     */
    public ClientSend(Socket client, String userName) {
        this.client = client;
        this.br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        this.isRunning = true;
        this.userName = userName;

        try {
            this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            send(userName);
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    /**
     * 客户端给服务端发送消息
     */
    @Override
    public void run() {
        while (this.isRunning) {
            String msg = getStrFromConsole();
            if (!"".equals(msg)) {
                send(msg);
            }
        }
    }

    /**
     * 从控制台获取消息
     * @return
     */
    private String getStrFromConsole() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    /**
     * 释放资源
     */
    private void release() {
        this.isRunning = false;
        Utils.release(client, dos, br);
    }
}
