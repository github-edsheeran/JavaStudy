package SXT._6NetworkProgram.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解网络爬虫以及模拟浏览器进行访问
 * @author: LiuDongMan
 * @createdDate: 2019-08-29 15:46
 **/
public class SpiderTest {
    public static void main(String[] args) {
        BufferedReader br = null;

        try {
//            // 获取URL
//            URL url = new URL("https://www.jd.com");
//
//            // 下载资源，将字节流转换为字符流
//            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            URL url = new URL("https://www.dianping.com");
            // 并不是所有的网站都可以直接获取，部分需要模拟浏览器才行
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");    // 请求方法
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36");  // 请求属性
            br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
            String msg = null;

            while ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 分析
        // 处理
    }
}
