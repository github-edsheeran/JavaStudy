package SXT._6NetworkProgram.part2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP多次交流的接收端
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 08:44
 **/
public class UDPTalkServer {
    public static void main(String[] args) {
        try (DatagramSocket dSocket = new DatagramSocket(8888)) {
            byte[] container = new byte[1024 * 60];
            DatagramPacket dPacket = new DatagramPacket(container, 0, container.length);
            byte[] datas = null;
            String msg = null;

            while (true) {
                dSocket.receive(dPacket);
                datas = dPacket.getData();
//                msg = new String(datas, 0, datas.length);
                // 这个地方必须通过调用DatagramPacket的getLength方法获取传输数据的真实大小，否则，会出现多余的无效数据的情况
                msg = new String(datas, 0, dPacket.getLength());

                if ("bye".equals(msg)) {
                    break;
                }

                System.out.println(msg);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
