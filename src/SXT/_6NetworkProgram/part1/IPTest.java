package SXT._6NetworkProgram.part1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解Java中的IP的相关知识
 * IP地址，定位一个节点，例如，计算机、路由或者通讯设备等
 * @author: LiuDongMan
 * @createdDate: 2019-08-29 09:50
 **/
public class IPTest {
    public static void main(String[] args) {
        try {
            // 获取本机的相关信息
            InetAddress ia = InetAddress.getLocalHost();
//            System.out.println(ia.getHostAddress());
//            System.out.println(ia.getHostName());

            // 通过域名获取相关信息
            ia = InetAddress.getByName("www.163.com");
//            System.out.println(ia.getHostAddress());
//            System.out.println(ia.getHostName());

            // 根据IP获取相关信息
            ia = InetAddress.getByName("221.233.240.32");
            System.out.println(ia.getHostAddress());
            // 输出IP而不是域名，如果这个IP地址不存在或者DNS服务器不允许进行ip地址和域名的映射，getHostName方法就直接返回这个IP地址
            System.out.println(ia.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
