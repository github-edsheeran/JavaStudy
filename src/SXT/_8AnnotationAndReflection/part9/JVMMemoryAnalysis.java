package SXT._8AnnotationAndReflection.part9;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: JVM内存分析，反射机制的核心原理
 * 类加载机制：
 * 1.JVM把class文件加载到内存，对数据进行校验、解析和初始化，最终形成JVM可以直接使用的java类型的过程；
 * 2.加载。把class文件字节码内容加载到内存中，并将这些静态数据转换成方法区中的运行时数据结构，在堆中生成一个代表这个类的java.lang.Class对象，
 * 作为方法区类数据的访问入口，这个过程需要类加载器参与；
 * 3.链接。将java类的二进制代码合并到JVM的运行状态之中的过程。
 * 验证：确保加载的类信息符合JVM规范，没有安全方面的问题；
 * 准备：正式为类变量(static变量)分配内存并设置类变量初始值的阶段(需要注意的是，对于static int a = 100来说，在准备过程中，a的值会被赋值为默认值0，100是在初始化的时候赋值的)，
 * 这些内存都将在方法区中进行分配；
 * 解析：虚拟机常量池内的符号引用替换为直接引用的过程；
 * 4.初始化。初始化阶段是执行类构造器<clinit>()方法的过程。类构造器<clinit>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块中的语句合并产生的。
 * 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先执行父类的初始化；
 * 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确加锁和同步；
 * 当访问一个java类的静态域时，只有真正声明这个域的类才会被初始化；
 * @author: LiuDongMan
 * @createdDate: 2019-09-20 09:29
 **/
public class JVMMemoryAnalysis {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.width);
    }
}

class A {
    // 类构造器是由所有类变量和静态语句块合并产生，这个动作由编译器进行，因此，这两段代码会优先执行
    public static int width = 200;

    static {
        System.out.println("静态初始化类A");
        width = 300;
    }

    public A() {
        System.out.println("创建类A对象");
    }
}
