package SXT._8AnnotationAndReflection.part11;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 自定义文件系统类加载器
 * @author: LiuDongMan
 * @createdDate: 2019-09-25 09:13
 **/
public class FileSystemClassLoader extends ClassLoader {
    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);    // 先看指定的类是否已经被加载

        if (aClass != null) {
            return aClass;
        } else {
            ClassLoader parent = this.getParent();  // 获取父加载器

            try {
                // 根据双亲委托机制，先用父加载器进行加载，如果不能加载，再使用子加载器进行加载，但是会出现父加载器无法加载而抛出异常的情况
                aClass = parent.loadClass(name);
            } catch (Exception e) {
//                e.printStackTrace();    // 出于学习目的，这个地方的异常打印可以注释掉
            }

            if (aClass != null) {
                return aClass;
            } else {
                // 如果父加载器无法加载，则最后由自定义的文件系统加载器进行加载
                byte[] classData = getClassData(name);

                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    aClass = defineClass(name, classData, 0, classData.length);
                }
            }
        }

        return aClass;
    }

    public FileSystemClassLoader() {
    }

    private byte[] getClassData(String name) {
        String path = this.rootDir + "/" + name.replace('.', '/') + ".class";
        File file = new File(path);
        byte[] flush01 = new byte[1024];
        int length = -1;

        try (InputStream is = new BufferedInputStream(new FileInputStream(file));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while ((length = is.read(flush01)) != -1) {
                baos.write(flush01, 0, length);
            }

            baos.flush();

            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        FileSystemClassLoader fscl01 = new FileSystemClassLoader("D:/JavaTest");
        FileSystemClassLoader fscl02 = new FileSystemClassLoader("D:/JavaTest");

        try {
            Class<?> aClass = fscl01.loadClass("SXT._8AnnotationAndReflection.part11.HelloWorld");
            Class<?> bClass = fscl01.loadClass("SXT._8AnnotationAndReflection.part11.HelloWorld");
            Class<?> cClass = fscl02.loadClass("SXT._8AnnotationAndReflection.part11.HelloWorld");
            Class<?> dClass = fscl01.loadClass("java.lang.String"); // 由于双亲委托机制的存在，可以利用启动(引导)类加载器加载核心包中的类
            Class<?> eClass = fscl01.loadClass("SXT._8AnnotationAndReflection.part11.FileSystemClassLoader");

            System.out.println(aClass.hashCode());
            System.out.println(bClass.hashCode());
            System.out.println(cClass.hashCode());  // 同一个类，被不同的加载器加载，JVM认为是不同的类
            System.out.println(cClass.getClassLoader());
            System.out.println(dClass.getClassLoader());    // 由于启动(引导)类加载器底层是由其他语言实现的，因此这个地方为null
            System.out.println(eClass.getClassLoader());    // 由于双亲委托机制的存在，会优先看父加载器是否能加载，这个时候，应用程序类加载器可以加载这个类，因此优先使用
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
