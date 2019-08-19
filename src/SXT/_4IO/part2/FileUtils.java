package SXT._4IO.part2;

import java.io.*;
import java.util.Scanner;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 将IO中的一些操作进行封装
 * @author: LiuDongMan
 * @createdDate: 2019-08-19 10:58
 **/
public class FileUtils {
    public static void main(String[] args) {
        // 文件到文件
//        try {
//            InputStream is = new FileInputStream("Files/1.txt");
//            OutputStream os = new FileOutputStream("Files/1copy.txt");
//            copy(is, os);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 文件到字节数组
        byte[] datas = null;
        try {
            InputStream is = new FileInputStream("Files/1.txt");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            copy(is, os);
            datas = os.toByteArray();
            System.out.println(os.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 字节数组到文件
        try {
            InputStream is = new ByteArrayInputStream(datas);
            OutputStream os = new FileOutputStream("Files/2copy.txt");
            copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(InputStream is, OutputStream os) {
        byte[] flush01 = new byte[1024];
        int length = -1;

        try {
            while ((length = is.read(flush01)) != -1) {
                os.write(flush01, 0, length);
            }

            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is, os);
        }
    }

    public static void close(Closeable... ios) {
        for (Closeable io : ios) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
