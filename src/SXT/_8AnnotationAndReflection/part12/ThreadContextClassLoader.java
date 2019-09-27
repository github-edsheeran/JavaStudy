package SXT._8AnnotationAndReflection.part12;

import SXT._8AnnotationAndReflection.part11.FileSystemClassLoader;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解线程上下文类加载器
 * @author: LiuDongMan
 * @createdDate: 2019-09-27 09:40
 **/
public class ThreadContextClassLoader {
    public static void main(String[] args) {
        ClassLoader loader = ThreadContextClassLoader.class.getClassLoader();   // 获取当前类所用的类加载器

        System.out.println(loader);

        loader = Thread.currentThread().getContextClassLoader();    // 获取当前线程所用的类加载器

        System.out.println(loader);

        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("D:/JavaTest")); // 设置当前线程所用的类加载器

        System.out.println(Thread.currentThread().getContextClassLoader());

        try {
            Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("SXT._8AnnotationAndReflection.part11.HelloWorld");

            System.out.println(aClass);
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
