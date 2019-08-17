package SXT._4IO.part1;

import java.io.UnsupportedEncodingException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解编码与解码
 * @author: LiuDongMan
 * @createdDate: 2019-08-17 15:24
 **/
public class EncodeAndDecode {
    public static void main(String[] args) {
        //encode();
        decode();
    }

    public static void encode() {
        // 默认的是项目中的编码格式，即，UTF-8，此时，英文是一个字节，中文是三个字节
        String msg = "你好世界a";
        byte[] bytes = msg.getBytes();
        System.out.println(bytes.length);
    }

    public static void decode() {
        String msg = "你好世界a";
        byte[] bytes = msg.getBytes();

        try {
            msg = new String(bytes, 0, bytes.length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(msg);

        /**
         * 出现乱码的原因:
         * 1.字节数不够;
         * 2.字符集不统一，编码的时候是一种字符集，解码的时候又是另一种字符集;
         */
        try {
            msg = new String(bytes, 0, bytes.length - 2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(msg);

        try {
            msg = new String(bytes, 0, bytes.length, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(msg);
    }
}
