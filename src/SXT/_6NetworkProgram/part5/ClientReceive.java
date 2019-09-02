package SXT._6NetworkProgram.part5;

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

        try {
            this.dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg = "";

        while (this.isRunning) {
            try {
                msg = dis.readUTF();

                if (!"".equals(msg)) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
