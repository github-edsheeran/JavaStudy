package SXT._8AnnotationAndReflection.part10;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 深入理解类加载器
 * 类加载器的作用：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区中的运行时数据结构，在堆中生成一个代表这个类的java.lang.Class对象，作为方法区
 * 类数据的访问入口；
 * 类缓存：标准的javaSE类加载器可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持加载(缓存)一段时间，不过，JVM垃圾收集器可以回收这些Class对象；
 * java.lang.ClassLoader类的基本职责就是根据一个指定的类的名字，找到或者生成其对应的字节代码，然后从这些字节代码中定义出一个java类，即java.lang.Class类的一个实例，
 * 除此之外，ClassLoader还负责加载java应用所需的资源，如图像文件和配置文件等；
 * 需要额外注意下loadClass这个方法，加载之前会先在方法区中寻找是否加载过这个类，如果已经加载，则直接返回Class对象，如果没有，则会按照双亲委托机制进行加载，如果最后没有一个类
 * 加载器可以加载，则抛出异常；
 * @author: LiuDongMan
 * @createdDate: 2019-09-24 11:04
 **/
public class FurtherClassLoader {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader()); // 获取应用程序(系统)类加载器
        System.out.println(ClassLoader.getSystemClassLoader().getParent()); // 获取扩展类加载器
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent()); // 启动(引导)类加载器底层是用其他语言实现的，因此这个地方为null
        System.out.println(System.getProperty("java.class.path"));  // 获取系统中path所指定的路径

        // 需要自己在src目录下新建java.lang.String类进行测试，其实实现toString方法
        String str = "Hello, world!";
        /**
         * 获取str对象所属类是由哪个类加载器加载的。由于自定义的String类也是java.lang包下的，这个时候，因为双亲委托机制的存在，使得这个类层层往上会一直回溯到启动(引导)类加载器
         * 进行加载，但是加载之前，发现已经加载过JAVA_HOME/jre/lib/rt.jar中的核心类java.lang.String，因此不会再进行加载，这个时候就保证了核心库的安全
         */
        System.out.println(str.getClass().getClassLoader());
        System.out.println(str);
    }
}
