package SXT._6NetworkProgram.part5;

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

    public ClientSend(Socket client) {
        this.client = client;

        try {
            this.dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
    }

    @Override
    public void run() {
        String msg = "";

        while (this.isRunning) {
            try {
                msg = br.readLine();

                if (!"".equals(msg)) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
