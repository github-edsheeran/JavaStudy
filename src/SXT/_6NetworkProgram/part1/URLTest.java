package SXT._6NetworkProgram.part1;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解Java中的URL的相关知识
 * URL，统一资源定位器，与http、html在互联网中有着十分重要的地位
 * 一个完整的URL：http://www.baidu.com:80/index.html?uname=liudongman&pwd=123456#a，其中，80是http协议的默认端口；#a是锚点
 * @author: LiuDongMan
 * @createdDate: 2019-08-29 15:03
 **/
public class URLTest {
    public static void main(String[] args) {
        try {
            // 并不会去验证URL是否有效
            URL url = new URL("http://www.baidu.com:80/index.html?uname=liudongman&pwd=123456#a");

            // 获取相关信息
            System.out.println("协议 --> " + url.getProtocol());
            System.out.println("域名或IP --> " + url.getHost());
            System.out.println("端口 --> " + url.getPort());
            System.out.println("请求资源1 --> " + url.getPath());
            System.out.println("请求资源2 --> " + url.getFile());
            System.out.println("参数 --> " + url.getQuery());
            System.out.println("锚点 --> " + url.getRef());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
