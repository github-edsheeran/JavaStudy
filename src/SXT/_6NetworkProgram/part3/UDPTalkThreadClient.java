package SXT._6NetworkProgram.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 通过UDP和多线程实现简单的互相咨询的功能
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 08:58
 **/
public class UDPTalkThreadClient implements Runnable {
    private DatagramSocket dSocket;
    private String toIP;
    private int toPort;
    private BufferedReader br;

    public UDPTalkThreadClient(int port, String toIP, int toPort) {
        this.toIP = toIP;
        this.toPort = toPort;
        this.br = new BufferedReader(new InputStreamReader(System.in));

        try {
            dSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg = null;
        DatagramPacket dPacket = null;
        byte[] datas = null;

        while (true) {
            try {
                msg = br.readLine();

                if ("bye".equals(msg)) {
                    break;
                }

                datas = msg.getBytes();
                dPacket = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress(toIP, toPort));
                dSocket.send(dPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dSocket.close();

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
