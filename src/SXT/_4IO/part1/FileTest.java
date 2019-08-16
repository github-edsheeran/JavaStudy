package SXT._4IO.part1;

import java.io.File;
import java.io.IOException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解File类的一些使用方式
 * @author: LiuDongMan
 * @createdDate: 2019-08-16 15:11
 **/
public class FileTest {
    public static void main(String[] args) {
        /**
         * 文件路径中分隔符的两种形式
         */
        String path01 = "D:\\WorkSpace\\IdeaWorkSpace\\JavaStudy\\Images\\IO.png";
        String path02 = "D:" + File.separator + "WorkSpace" + File.separator  + "IdeaWorkSpace" + File.separator +
                "JavaStudy" + File.separator + "Images" + File.separator + "IO.png";

        // 推荐使用这种方式
        String path03 = "D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png";
//        System.out.println(path01);
//        System.out.println(path02);

        /**
         * 创建File对象的三种形式
         * 需要注意的是，File对象只是跟系统文件或者文件夹建立连接，但是文件是否存在，在未使用对象前是不知道的
         */
        File file01 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file02 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images", "IO.png");
        File file03 = new File(new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images"), "IO.png");
//        System.out.println("文件绝对路径: " + file01.getAbsolutePath() + "; 文件大小: " + file01.length());
//        System.out.println("文件绝对路径: " + file02.getAbsolutePath() + "; 文件大小: " + file02.length());
//        System.out.println("文件绝对路径: " + file03.getAbsolutePath() + "; 文件大小: " + file03.length());

        /**
         * 绝对路径和相对路径
         */
        File file04 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file05 = new File("Images/IO.png");
//        System.out.println("文件绝对路径: " + file04.getAbsolutePath() + "; 文件大小: " + file04.length());
//        System.out.println("文件绝对路径: " + file05.getAbsolutePath() + "; 文件大小: " + file05.length());
//        System.out.println(System.getProperty("user.dir")); // 相对路径相对的目录

        // getPath方法，如果传入的是绝对路径，则返回的是绝对路径；反之，则是相对路径
        File file06 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file07 = new File("Images/IO.png");
//        System.out.println(file06.getPath());
//        System.out.println(file07.getPath());

        // getParent方法，返回的是文件或文件夹前面的部分，如果传入的时候，没有前面部分，则返回null
        File file08 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file09 = new File("IO.png");
//        System.out.println(file08.getParent());
//        System.out.println(file09.getParent());

        // getParentFile方法，根据传入的路径返回一个父文件或文件夹的对象，如果不存在，则返回null，判断方式和getParent类似
        File file10 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images");
        File file11 = new File("Images");
//        System.out.println(file10.getParentFile().getName());
//        System.out.println(file11.getParentFile());

        /**
         * exists判断文件或文件夹是否存在
         * isFile判断是否为文件
         * isDirectory判断是否为文件夹
         */
        File file12 = new File("JavaStudy/Images/IO.png");  // 注意，相对路径，相对的是项目的路径，如果加入项目文件夹名，此时的相对路径会有问题，因此是否存在判断会false
        File file13 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file14 = new File("Images");
//        System.out.println(file12.exists());
//        System.out.println(file13.exists());
//        System.out.println(file14.isDirectory());
//        System.out.println(file14.isFile());

        // length方法返回的是文件的长度，对于文件夹会有专门的方法进行判断，需要注意的是，如果文件或文件夹不存在，以及是文件夹的情况，都会返回0
        File file15 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/IO.png");
        File file16 = new File("Images");
//        System.out.println(file15.length());
//        System.out.println(file16.length());

        // createNewFile方法，只会创建文件，并且只会在文件不存在的情况下进行创建，需要注意的是，文件名需要符合所在操作系统的规定
        File file17 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/OI.png");
//        try {
//            System.out.println(file17.createNewFile());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // delete方法
        File file18 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/Images/OI.png");
        System.out.println(file18.delete());
    }
}
