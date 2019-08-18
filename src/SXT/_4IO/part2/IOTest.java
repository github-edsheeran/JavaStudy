package SXT._4IO.part2;

import java.io.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解IO流
 * @author: LiuDongMan
 * @createdDate: 2019-08-17 17:17
 **/
public class IOTest {
    /**
     * 通过FileInputStream实现单个字节读取文件的功能
     */
    public static void exampleOne() {
        File file = new File("Files/1.txt");    // 1.创建源
        InputStream inputStream = null; // 2.选择流

        try {
            inputStream = new FileInputStream(file);
            int temp;

            // 3.操作读取数据
            while ((temp = inputStream.read()) != -1) {
//                System.out.print((char) inputStream.read());  // 这种方式不可取，因为循环判断的时候已经从流中取了一次数据
                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 防止出现流对象引用还是空的异常情况
                if (inputStream != null) {
                    inputStream.close();    // 4.释放
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileInputStream实现多个字节读取文件的功能
     */
    public static void exampleTwo() {
        File file = new File("Files/1.txt");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            byte[] flush = new byte[1024];  // 实际中一般都是读取单位计数的大小
            int length = -1;

            while ((length = inputStream.read(flush)) != -1) {
                // 注意length参数，如果只是用字节数组生成字符串，由于数组在创建的时候就会指定大小，结果会出现一些不必要的多余数据
                System.out.println(new String(flush, 0, length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileOutputStream实现输入内容到文件的功能
     */
    public static void exampleThree() {
        // 输入流中，需要保证文件必须存在；而在输出流中，如果文件不存在，则会创建一个文件
        File file = new File("Files/2.txt");
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
//            outputStream = new FileOutputStream(file, true);  // 如果为true，则是在文件内容后面追加内容；默认为false，即重写
            String msg = "I am LiuDongMan!";
            byte[] flush = msg.getBytes();
            outputStream.write(flush, 0, flush.length);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileInputStream和FileOutputStream实现文件的拷贝功能
     * 感兴趣的话可以使用递归实现文件夹及文件的拷贝功能
     */
    public static void exampleFour() {
        File inFile = new File("Files/2.txt");
        File outFile = new File("Files/3.txt");
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(inFile);
            outputStream = new FileOutputStream(outFile);
            byte[] inFlush = new byte[1024];
            int length = -1;
            StringBuilder sb = new StringBuilder();

            // 一边输入一边输出
            while ((length = inputStream.read(inFlush)) != -1) {
                outputStream.write(inFlush, 0, length);
            }

            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 如果存在多个IO流，则遵循先打开的后关闭原则，且彼此独立
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileReader实现单个字符读取文件的功能
     * 虽然有着自己的一些特性，但是大体上和字节差不多
     */
    public static void exampleFive() {
        File file = new File("Files/1.txt");
        Reader reader = null;

        try {
            reader = new FileReader(file);
            int temp;

            while ((temp = reader.read()) != -1) {
                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileReader实现多个字符读取文件的功能
     */
    public static void exampleSix() {
        File file = new File("Files/1.txt");
        Reader reader = null;

        try {
            reader = new FileReader(file);
            char[] flush = new char[1024];
            int length = -1;

            while ((length = reader.read(flush)) != -1) {
                System.out.print(new String(flush, 0, length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过FileWriter实现输入内容到文件的功能
     */
    public static void exampleSeven() {
        File file = new File("Files/2.txt");
        Writer writer = null;

        try {
            writer = new FileWriter(file);
            String msg = "I am LiuDongMan!我是刘东满！";
//            char[] flush = msg.toCharArray();
//            writer.write(flush, 0, flush.length);
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过ByteArrayInputStream实现单个字节从内存中读取的功能
     */
    public static void exampleEight() {
        byte[] src = "Talk is cheap, show me the code!".getBytes();
        InputStream is = new ByteArrayInputStream(src);
        int temp;

        // 由于是从内存中读取数据，因此不需要通知操作系统进行关闭操作，相应的资源会通过垃圾回收机制进行释放
        try {
            while ((temp = is.read()) != -1) {
                System.out.print((char) temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过ByteArrayInputStream实现多个字节从内存中读取的功能
     */
    public static void exampleNine() {
        byte[] src = "Talk is cheap, show me the code!".getBytes();
        InputStream is = new ByteArrayInputStream(src);
        int length = -1;
        byte[] flush = new byte[5];

        try {
            while ((length = is.read(flush)) != -1) {
                System.out.print(new String(flush, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过ByteArrayOutputStream实现输出功能
     */
    public static void exampleTen() {
        byte[] dest = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   // 由于要使用到子类自己的方法，因此不使用多态
        byte[] src = "Talk is cheap, show me the code!".getBytes();
        baos.write(src, 0, src.length);

        try {
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dest = baos.toByteArray();
        System.out.println(new String(dest));
    }

    public static void main(String[] args) {
//        exampleOne();
//        exampleTwo();
//        exampleThree();
//        exampleFour();
//        exampleFive();
//        exampleSix();
//        exampleSeven();
//        exampleEight();
//        exampleNine();
        exampleTen();
    }
}
