package SXT._4IO.part6;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: CommonsIO中的常规核心操作
 * 程序中只列出了部分常用的操作，更多的内容需要查阅API文档和相关资料
 * @author: LiuDongMan
 * @createdDate: 2019-08-27 08:53
 **/
public class CIOCommonCoreOperation {
    public static void main(String[] args) {
        // 文件大小
        int length = (int) FileUtils.sizeOf(new File("src/SXT/_4IO/part2/NodeIOTest.java"));
//        System.out.println(length);

        // 文件夹大小
        length = (int) FileUtils.sizeOf(new File("src/SXT/_4IO/part2"));
//        System.out.println(length);

        // 遍历文件夹下的文件，第三个参数如果为空的话，代表只遍历当前文件夹；EmptyFileFilter.NOT_EMPTY表示只遍历非空文件，更多的组合需要自己看API或者查找相关资料
        Collection<File> collection = FileUtils.listFiles(new File("src/SXT/_4IO"), EmptyFileFilter.NOT_EMPTY, null);
        collection = FileUtils.listFiles(new File("src/SXT/_4IO"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
        collection = FileUtils.listFiles(new File("src/SXT/_4IO"), FileFilterUtils.and(new SuffixFileFilter("java"), new PrefixFileFilter("E")),
                DirectoryFileFilter.INSTANCE);

//        for (File file : collection) {
//            System.out.println(file.getAbsolutePath());
//        }

        // 读取文件
        String msg = null;
        List<String> msgList = null;
        byte[] msgBytes = null;
        try {
            // 以指定的字符集读取文件中的内容，返回字符串
            msg = FileUtils.readFileToString(new File("Files/1.txt"), "UTF-8");
//            System.out.println(msg);

            // 以指定的字符集读取文件中的内容，返回字符串集合
            msgList = FileUtils.readLines(new File("Files/1.txt"), "UTF-8");
//            for (String s : msgList) {
//                System.out.println(s);
//            }

            // 将文件转换为字节数组
            msgBytes = FileUtils.readFileToByteArray(new File("Files/1.txt"));
//            System.out.println(msgBytes.length);

            // 以迭代器的方式返回文件内容
            LineIterator lineIterator = FileUtils.lineIterator(new File("Files/1.txt"), "UTF-8");
//            while (lineIterator.hasNext()) {
//                System.out.println(lineIterator.nextLine());
//            }

            // 写出单个字符串内容，第四个参数代表是否追加
//            FileUtils.write(new File("Files/1.txt"), "这工具是真的骚\r\n", "UTF-8", true);

            // 写出集合内容，第三个参数代表数据间的间隔符
            List<String> writeList = new ArrayList<>();
//            writeList.add("一二三四五\r\n");
//            writeList.add("汗滴禾下土\r\n");
//            writeList.add("一些小字符\r\n");
//            writeList.add("一写一下午\r\n");
//            FileUtils.writeLines(new File("Files/1.txt"), writeList, "-->", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        copy();
    }

    public static void copy() {
        try {
            // 复制文件
//            FileUtils.copyFile(new File("Files/4.txt"), new File("Files/4copy.txt"));

            // 复制文件到目录
//            FileUtils.copyFileToDirectory(new File("Images/IO.png"), new File("Files"));

            // 复制目录
//            FileUtils.copyDirectory(new File("Files"), new File("Files01"));

            // 复制目录到指定的目录下
//            FileUtils.copyDirectoryToDirectory(new File("Files01"), new File("Files"));

            // 复制指定URL中的内容到文件
//            FileUtils.copyURLToFile(new URL("https://www.163.com"), new File("Files/163.html"));

            // 复制指定URL中的内容到字符串
            String datas = IOUtils.toString(new URL("https://www.163.com/"), "GBK");
            System.out.println(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
