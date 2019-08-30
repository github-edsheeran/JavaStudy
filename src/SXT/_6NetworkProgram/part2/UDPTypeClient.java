package SXT._6NetworkProgram.part2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输基本数据类型发送端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 14:14
 **/
public class UDPTypeClient {
    public static void main(String[] args) {
        System.out.println("发送端启动中......");

        DatagramSocket dSocket = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos))) {
            dSocket = new DatagramSocket(6666);

            dos.writeUTF("Hello, world!");
            dos.writeBoolean(true);
            dos.flush();    // 额外注意

            byte[] datas = baos.toByteArray();
            DatagramPacket dPacket = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8888));

            dSocket.send(dPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();
        }
    }
}
