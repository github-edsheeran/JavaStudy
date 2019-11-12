package SXT._4IO.part4;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

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
     * 1.以字符流的形式操作字节流（前提是知晓内容为纯文本）
     * 2.可以指定具体的字符集
     * 需要注意的是，转换的字符流其字符集默认为项目的字符集，因此，最好手动指明需要转换成哪种字符集
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

        // 将字节流转换为字符流，并指定具体的字符集
//        try (InputStreamReader reader = new InputStreamReader(new URL("http://www.baidu.com").openStream(), "UTF-8")) {
//            int temp = -1;
//
//            while ((temp = reader.read()) != -1) {
//                System.out.print((char) temp);
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 字节流，因此存在字节数不够出现乱码的问题
//        try (InputStream is = new URL("http://www.baidu.com").openStream()) {
//            int temp = -1;
//
//            while ((temp = is.read()) != -1) {
//                System.out.print((char) temp);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 将字节流转换为字符流，实现下载百度源码的功能
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://www.baidu.com").openStream(), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Files/baidu.html")))) {
            String msg = "";

            while ((msg = reader.readLine()) != null) {
                writer.write(msg);
                writer.newLine();
            }

            writer.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单理解数据流DataInputStream和DataOutputStream
     * 需要注意的是，怎么写进去的，就要怎么读出来，且按顺序
     */
    public static void exampleFour() {
        DataInputStream dis = null;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos))) {
            dos.writeUTF("Hello, world!");  // 将数据写入到内存中
            dos.writeBoolean(true);
            dos.writeChar('a');
            dos.writeDouble(2);
            dos.flush();    // 额外注意

            byte[] flush = baos.toByteArray();  // 将写入到内存中的数据存入到字节数组中
            System.out.println(flush.length);
            dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(flush)));

            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 简单理解对象流ObjectInputStream和ObjectOutputStream
     */
    public static void exampleFive() {
        // 将对象序列化到内存中，再通过反序列化读出来，依然遵循顺序一致的原则
//        ObjectInputStream ois = null;
//
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos))) {
//            oos.writeObject("Hello, world!");
//            oos.writeObject(new Date());
//            oos.writeObject(new Employee("张三", 2000));
//            oos.flush();
//
//            byte[] flush = baos.toByteArray();
//            ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(flush)));
//
//            Object a = ois.readObject();
//            Object b = ois.readObject();
//            Object c = ois.readObject();
//
//            if (a instanceof String) {
//                System.out.println((String) a);
//            }
//
//            if (b instanceof Date) {
//                System.out.println((Date) b);
//            }
//
//            if (c instanceof Employee) {
//                Employee employee = (Employee) c;
//                System.out.println(employee.getName() + " --> " + employee.getSalary());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // 将对象序列化到文件中，再通过反序列化读出来
        File inFile = new File("Files/obj.ser");
        File outFile = new File("Files/obj.ser");
        ObjectInputStream ois = null;

        try (OutputStream os = new FileOutputStream(outFile);
             ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os))) {
            oos.writeObject("Hello, world!");
            oos.writeObject(new Date());
            oos.writeObject(new Employee("张三", 200));
            oos.flush();    // 额外注意

            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inFile)));
            Object a = ois.readObject();
            Object b = ois.readObject();
            Object c = ois.readObject();

            if (a instanceof String) {
                System.out.println((String) a);
            }

            if (b instanceof Date) {
                System.out.println((Date) b);
            }

            if (c instanceof Employee) {
                Employee employee = (Employee) c;
                System.out.println(employee.getName() + " --> " + employee.getSalary());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单理解打印流PrintStream和PrintWriter
     * 了解即可，知道有什么作用
     */
    public static void exampleSix() {
//        PrintStream ps = System.out;
//        ps.println("a");
//        ps.println(true);
//
//        try {
//            // true表示是否自动flush
//            ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("Files/4.txt")), true);
//            ps.println("b");
//            ps.println(true);
//
//            // 重定向输出端到文件，System.out.print相关的内容会输出到文件中去
//            System.setOut(ps);
//
//            System.out.println("c");
//            System.out.println("true");
//
//            // 重定向输出端到控制台
//            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
//            System.out.println("I'm back!");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            ps.close();
//        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("Files/4.txt")));
            pw.println("a");
            pw.println(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * 简单理解
     */
    public static void exampleSeven() {
    }

    public static void main(String[] args) {
//        exampleOne();
//        exampleTwo();
//        exampleThree();
        exampleFour();
//        exampleFive();
//        exampleSix();
    }
}

/**
 * 需要实现Serializable接口才能达到序列化和反序列化的效果
 */
class Employee implements Serializable {
    private transient String name;  // 被transient修饰，表示不用被序列化
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
