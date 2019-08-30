package SXT._6NetworkProgram.part2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP中的发送端
 * 1.使用DatagramSocket指定端口后，创建发送端；
 * 2.准备数据，一定要转换成字节数组；
 * 3.将数组封装成DatagramPacket包裹，需要指定目的地；
 * 4.发送包裹，无需知晓对方是否收到；
 * 5.释放资源；
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 09:50
 **/
public class UDPDataClient {
    public static void main(String[] args) {
        System.out.println("发送端启动中......");

        DatagramSocket ds = null;

        try {
            // 使用DatagramSocket指定端口后，创建发送端
            ds = new DatagramSocket(8888);
            // 准备数据，一定要转换成字节数组
            String msg = "Hello, world!";
            byte[] datas = msg.getBytes();
            // 将数组封装成DatagramPacket包裹，需要指定目的地
            DatagramPacket dp = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 6666));
            // 发送包裹
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            ds.close();
        }
    }
}
