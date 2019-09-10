package SXT._8AnnotationAndReflection.part3;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 理解反射对象的几种方式，以及各种类型对应的反射对象如何获取
 * 程序运行时，可以改变程序结构或变量类型，称为动态语言，虽然java不是动态语言，但是利用反射可以实现动态性
 * 反射指的是可以于运行时加载、探知、使用编译期间完全未知的类
 * 程序在运行状态中，可以动态加载一个只有名称的类，对于任意一个已加载的类，都能够知道这个类的所有属性和方法，对于任意一个对象，都能够调用它的任意
 * 一个方法和属性
 * 加载完类后，在堆内存中，就产生了一个Class类型的对象，注意，一个类只有一个Class对象，这个对象就包含了完整的类的结构信息，可以通过这个对象看到
 * 类的结构，这个对象就像一面镜子，所以，称之为反射
 * 常见作用：
 * 1.动态加载类、动态获取类信息（属性、方法、构造器）；
 * 2.动态构造对象；
 * 3.动态调用类和对象的任意方法、构造器；
 * 4.动态调用和处理属性；
 * 5.获取泛型信息；
 * 6.处理注解；
 * @author: LiuDongMan
 * @createdDate: 2019-09-10 09:32
 **/
public class ReflectionTest {
    public static void main(String[] args) {
        /**
         * 反射的三种方式：
         * 1.类名.class；
         * 2.对象.getClass()；
         * 3.Class.forName("完整的路径")，这个经常使用；
         * 需要注意的是，一个类只有一个类对象，因此下面三个对象的hashCode是相等的
         */
        Class aClass = User.class;
//        System.out.println(aClass.hashCode());

        aClass = new User().getClass();
//        System.out.println(aClass.hashCode());

        try {
            aClass = Class.forName("SXT._8AnnotationAndReflection.part3.User");
//            System.out.println(aClass.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 对于数组来说，长度不会影响类对象，只有数据类型和维度会影响
        Class bCLass = new int[10].getClass();
//        System.out.println(bCLass.hashCode());

        bCLass = new int[20].getClass();
//        System.out.println(bCLass.hashCode());

        bCLass = new double[10].getClass();
//        System.out.println(bCLass.hashCode());

        bCLass = new double[10][2].getClass();
//        System.out.println(bCLass.hashCode());

        // 基本数据类型甚至void也有着自己对应的类对象
        Class cClass = int.class;
        System.out.println(cClass.hashCode());

        cClass = void.class;
        System.out.println(cClass.hashCode());
    }
}
