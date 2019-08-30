package SXT._6NetworkProgram.part2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输文件发送端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 15:17
 **/
public class UDPFileClient {
    public static void main(String[] args) {
        System.out.println("发送端启动中......");

        DatagramSocket dSocket = null;

        try (InputStream is = new FileInputStream(new File("Images/emoji.jpg"));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            dSocket = new DatagramSocket(6666);
            byte[] flush01 = new byte[1024];
            int length = -1;

            while ((length = is.read(flush01)) != -1) {
                baos.write(flush01, 0, length);
            }

            baos.flush();

            byte[] datas = baos.toByteArray();
            DatagramPacket dPacket = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8888));
            dSocket.send(dPacket);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();
        }
    }
}
