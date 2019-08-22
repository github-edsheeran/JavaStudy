package SXT._4IO.part4;

import java.io.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解处理IO流
 * @author: LiuDongMan
 * @createdDate: 2019-08-22 15:56
 **/
public class HandlerIOTest {
    /**
     * 利用字节缓冲流BufferedInputStream对字节流InputStream进行修饰，提高性能
     */
    public static void exampleOne() {
        File inFile = new File("Files/1.txt");
        File outFile = new File("Files/1copy.txt");

        try (InputStream is = new BufferedInputStream(new FileInputStream(inFile));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(outFile))) {
            /**
             * 用字节缓冲流进行修饰，提高性能，之前的节点流测试代码类NodeIOTest中的部分例子都可以进行改造，为了更好的显示提高性能的效果，
             * 可以对视频进行拷贝
             */
            long t1 = System.currentTimeMillis();
            byte[] flush01 = new byte[1024];
            int length = -1;

            while ((length = is.read(flush01)) != -1) {
                os.write(flush01, 0, length);
            }

            os.flush();
            long t2 = System.currentTimeMillis();

            System.out.println(t2 - t1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用字符缓冲流进行修饰，提高性能
     */
    public static void exampleTwo() {
        File inFile = new File("Files/1.txt");
        File outFile = new File("Files/1copy.txt");

        // 为了使用到子类的自身特性，不能使用多态
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            long t1 = System.currentTimeMillis();
            String line = null;

            // 逐行读取
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();   // 换行
            }

            writer.flush();
            long t2 = System.currentTimeMillis();

            System.out.println(t2 - t1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用转换流将字节流转成字符流
     * 个人理解：利用转换流将字节流转换为字符流之后，字符流同时也具备了字节流的一些特性，例如这个例子当中的System.in和System.out
     */
    public static void exampleThree() {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
//            String msg = "";
//
//            while (!msg.equals("exit")) {
//                msg = reader.readLine();    // 循环读取
//                writer.write(msg);  // 循环写出
//                writer.newLine();   // 换行
//                writer.flush(); // 强制刷新，否则由于缓冲区的存在，导致输入不会立马输出
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public static void main(String[] args) {
//        exampleOne();
//        exampleTwo();
        exampleThree();
    }
}
