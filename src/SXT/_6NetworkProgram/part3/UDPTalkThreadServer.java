package SXT._6NetworkProgram.part3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 通过UDP和多线程实现简单的互相咨询的功能
 * @author: LiuDongMan
 * @createdDate: 2019-08-31 08:59
 **/
public class UDPTalkThreadServer implements Runnable {
    private DatagramSocket dSocket;
    private String name;

    public UDPTalkThreadServer(int port, String name) {
        try {
            this.dSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        this.name = name;
    }

    @Override
    public void run() {
        DatagramPacket dPacket = null;
        byte[] container = null;
        byte[] datas = null;

        while (true) {
            // 循环体外只能放对象的引用，新建对象以及相应的赋值操作只能放在循环体内，否则内容都是一样的
            container = new byte[1024 * 60];
            dPacket = new DatagramPacket(container, 0, container.length);

            try {
                dSocket.receive(dPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            datas = dPacket.getData();
            String msg = new String(datas, 0, dPacket.getLength());

            if ("bye".equals(msg)) {
                break;
            }

            System.out.println(this.name + ": " + msg);
        }

        dSocket.close();
    }
}
