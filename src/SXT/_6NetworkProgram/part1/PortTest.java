package SXT._6NetworkProgram.part1;

import java.net.InetSocketAddress;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解Java中的端口的相关知识
 * 1.用来区分软件；
 * 2.用两个字节来表示，0~65535；
 * 3.同一个协议下的端口号不能冲突；
 * 4.定义端口越大越好；
 * @author: LiuDongMan
 * @createdDate: 2019-08-29 10:11
 **/
public class PortTest {
    public static void main(String[] args) {
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 8080);
        // 获取主机名、带端口的IP地址以及端口号
        System.out.println(isa.getHostName() + " --> " + isa.getAddress() + " --> " + isa.getPort());

        isa = new InetSocketAddress("localhost", 9000);
        System.out.println(isa.getHostName() + " --> " + isa.getAddress() + " --> " + isa.getPort());
    }
}
