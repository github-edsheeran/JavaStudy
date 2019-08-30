package SXT._6NetworkProgram.part2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输对象接收端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 14:46
 **/
public class UDPObjectServer {
    public static void main(String[] args) {
        System.out.println("接收端启动中......");

        DatagramSocket dSocket = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        try {
            dSocket = new DatagramSocket(8888);
            byte[] container = new byte[1024 * 60];
            DatagramPacket dPacket = new DatagramPacket(container, 0, container.length);

            dSocket.receive(dPacket);

            byte[] datas = dPacket.getData();
            bais = new ByteArrayInputStream(datas);
            ois = new ObjectInputStream(new BufferedInputStream(bais));

            Object a = ois.readObject();
            Object b = ois.readObject();

            if (a instanceof Employee) {
                Employee employee = (Employee) a;
                System.out.println(employee.getName() + " --> " + employee.getAge());
            }

            if (b instanceof Date) {
                System.out.println((Date) b);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
