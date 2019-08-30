package SXT._6NetworkProgram.part2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输文件接收端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 15:18
 **/
public class UDPFileServer {
    public static void main(String[] args) {
        System.out.println("接收端启动中......");

        DatagramSocket dSocket = null;

        try (OutputStream os = new FileOutputStream(new File("Images/emoji-copy.jpg"))) {
            dSocket = new DatagramSocket(8888);
            byte[] container = new byte[1024 * 60];
            DatagramPacket dPacket = new DatagramPacket(container, 0, container.length);
            dSocket.receive(dPacket);
            byte[] datas = dPacket.getData();

            os.write(datas, 0, datas.length);
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
