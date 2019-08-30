package SXT._6NetworkProgram.part2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输基本数据类型接收端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 14:15
 **/
public class UDPTypeServer {
    public static void main(String[] args) {
        System.out.println("接收端启动中......");

        DatagramSocket dSocket = null;
        ByteArrayInputStream bais = null;
        DataInputStream dis = null;

        try {
            dSocket = new DatagramSocket(8888);
            byte[] container = new byte[1024 * 60];
            DatagramPacket dPacket = new DatagramPacket(container, 0, container.length);

            dSocket.receive(dPacket);

            byte[] datas = dPacket.getData();
            bais = new ByteArrayInputStream(datas);
            dis = new DataInputStream(new BufferedInputStream(bais));

            System.out.println(dis.readUTF());
            System.out.println(dis.readBoolean());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();

            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
