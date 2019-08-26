package SXT._4IO.part5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 利用RandomAccessFile实现文件的分割读取操作，日常使用较少，理解其中的理念即可
 * @author: LiuDongMan
 * @createdDate: 2019-08-26 10:24
 **/
public class DivideFile01 {
    public static void main(String[] args) {
        File inFile = new File("src/SXT/_4IO/part2/NodeIOTest.java");   // 需要分割的文件对象
        long totalSize = inFile.length(); // 文件对象的总大小
        int singleSize = 1024;  // 分割之后的每一块的大小
        int blockCount = (int) Math.ceil(totalSize * 1.0 / singleSize); // 需要分割成多少块
//        int blockCount = (int) (totalSize / singleSize + 1);  // 当能够整除时，分割的块数会有问题
        int beginPos;   // 起始位置
        int actualSize; // 实际大小，因为会出现文件大小或者最后一块的大小不够每一块的大小的情况

        for (int i = 0; i < blockCount; i++) {
            beginPos = i * singleSize;

            if (i == blockCount - 1) {
                actualSize = (int) (totalSize);
            } else {
                actualSize = singleSize;
                totalSize -= actualSize;
            }

            System.out.println("第" + (i + 1) + "块, 起始位置为: " + beginPos + ", 实际大小为: " + actualSize);
            divide(i, beginPos, actualSize, inFile);
        }
    }

    public static void divide(int blockNum, int beginPos, int actualSize, File inFile) {
        try (RandomAccessFile raf = new RandomAccessFile(inFile, "r")) {    // 读取中文会存在问题
            // 从指定位置开始，读取后面剩余的内容
            raf.seek(beginPos);

            int length = -1;
            byte[] flush01 = new byte[512];

            while ((length = raf.read(flush01)) != -1) {
                if (length >= actualSize) {
                    System.out.println(new String(flush01, 0, actualSize));
                    break;
                } else {
                    System.out.println(new String(flush01, 0, length));
                    actualSize -= length;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
