package SXT._6NetworkProgram.part2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现UDP传输对象发送端
 * @author: LiuDongMan
 * @createdDate: 2019-08-30 14:45
 **/
public class UDPObjectClient {
    public static void main(String[] args) {
        System.out.println("发送端启动中......");

        DatagramSocket dSocket = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos))) {
            dSocket = new DatagramSocket(6666);

            oos.writeObject(new Employee("张三", 20));
            oos.writeObject(new Date());
            oos.flush();

            byte[] datas = baos.toByteArray();
            DatagramPacket dPacket = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8888));
            dSocket.send(dPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dSocket.close();
        }
    }
}

class Employee implements Serializable {
    private transient String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }
}
