package SXT._4IO.part1;

import java.io.File;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解File类的一些使用方式
 * @author: LiuDongMan
 * @createdDate: 2019-08-16 15:11
 **/
public class FileTest {
    private String path;
    private int size;
    private File src;
    private int fileCount;
    private int dirCount = -1;  // 如果传入的是文件夹路径，这个时候遍历文件夹个数的时候会把自己也给算上，因此赋值-1

    public FileTest() {
    }

    public FileTest(String path) {
        this.path = path;
        this.src = new File(path);
        countDirSize(src);
    }

    /**
     * 根据传入的目录文件对象，统计文件夹中所有文件的大小，以及文件夹和文件的个数
     * @param file
     */
    private void countDirSize(File file) {
        if (null != file && file.exists()) {
            if (file.isFile()) {
                size += file.length();
                fileCount++;
            } else {
                dirCount++;
                for (File listFile : file.listFiles()) {
                    countDirSize(listFile);
                }
            }
        }
    }

    /**
     * 打印子孙级目录和文件的名称
     * @param file 传入的目录文件对象
     * @param level 所在层级
     */
    public void filesRecursion(File file, int level) {
        // 根据所在层级打印文件前缀符
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }

        System.out.println(file.getName());

        // 递归头
        if (null == file || !file.exists()) {
            return;
        } else if (file.isDirectory()) {    // 递归体
            for (File f : file.listFiles()) {
                filesRecursion(f, level + 1);   // 注意，这个地方不能使用自增操作，这样会让level自身的值加上1
            }
        }
    }

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
//        System.out.println(file18.delete());

        /**
         * mkdir: 创建文件夹，如果父目录不存在，则创建失败
         * mkdirs: 创建文件夹，如果父目录不存在，则一起创建
         * list: 返回下级文件和文件夹的名称，为字符串数组
         * listFiles: 返回下级文件和文件夹，为File数组
         * listRoots: 返回所有的根路径
         */
        File file19 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy");
        String[] subNames = file19.list();
//        for (int i = 0; i < subNames.length; i++) {
//            System.out.println(subNames[i]);
//        }

        File file20 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy");
        File[] subFiles = file20.listFiles();
//        for (int i = 0; i < subFiles.length; i++) {
//            System.out.println(subFiles[i].getAbsolutePath());
//        }

        File file21 = new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy");
        File[] roots = file21.listRoots();
//        for (int i = 0; i < roots.length; i++) {
//            System.out.println(roots[i].getAbsolutePath());
//        }

        FileTest test = new FileTest("D:/WorkSpace/IdeaWorkSpace/JavaStudy/src/SXT");
//        test.filesRecursion(new File("D:/WorkSpace/IdeaWorkSpace/JavaStudy/src/SXT"), 0);
//        System.out.println(test.getSize());
        System.out.println("文件夹个数: " + test.getDirCount() + "; 文件个数: " + test.getFileCount());
    }

    public int getSize() {
        return size;
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirCount() {
        if (dirCount < 0) {
            return 0;
        }

        return dirCount;
    }
}
