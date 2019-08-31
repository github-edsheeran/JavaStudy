package SXT._6NetworkProgram.part2;

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
 * @chineseDescription: 简单实现UDP多次交流的发送端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 08:44
 **/
public class UDPTalkClient {
    public static void main(String[] args) {
        try (DatagramSocket dSocket = new DatagramSocket(6666);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String msg = null;
            byte[] datas = null;
            DatagramPacket dPacket = null;

            while (true) {
                msg = br.readLine();

                if ("bye".equals(msg)) {
                    break;
                }

                datas = msg.getBytes();
                dPacket = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8888));
                dSocket.send(dPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
