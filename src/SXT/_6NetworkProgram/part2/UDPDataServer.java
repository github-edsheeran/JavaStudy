package SXT._6NetworkProgram.part2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP中的接收端
 * 1.使用DatagramSocket指定端口，创建接收端；
 * 2.准备接收容器，封装成DatagramPacket包裹
 * 3.阻塞式接收包裹；
 * 4.分析数据；
 * 5.释放资源；
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 09:55
 **/
public class UDPDataServer {
    public static void main(String[] args) {
        System.out.println("接收端启动中......");

        DatagramSocket ds = null;

        try {
            // 使用DatagramSocket指定端口，创建接收端
            ds = new DatagramSocket(6666);
            // 准备接收容器，封装成DatagramPacket包裹
            byte[] container = new byte[1024 * 60];
            DatagramPacket dp = new DatagramPacket(container, 0, container.length);
            // 阻塞式接收包裹
            ds.receive(dp);
            // 分析数据
            byte[] datas = dp.getData();
            int length = dp.getLength();

            System.out.println(new String(datas, 0, length));
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
