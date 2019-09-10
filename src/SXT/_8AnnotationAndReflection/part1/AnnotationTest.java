package SXT._8AnnotationAndReflection.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解注解
 * 1.注解可以起到注释的作用；
 * 2.可以被其他程序读取，附加在package、class、method、field上面，相当于添加了额外的辅助信息，后面可以通过反射机制编程实现对这些元数据的访问；
 * 3.几个常见的系统注解：@Override：重写方法、@Deprecated：不鼓励使用、@SuppressWarnings：抑制编译警告；
 * @author: LiuDongMan
 * @createdDate: 2019-09-09 10:49
 **/
public class AnnotationTest {
    /**
     * 重写方法
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * 不推荐使用
     */
    @Deprecated
    public static void test01() {

    }

    /**
     * 压制警告，需要注意的是，SuppressWarnings这个注解类中，String[] value()是属性，而不是方法；
     */
    @SuppressWarnings(/*value = {"all"}*/"all")
    public static void test02() {
        List list = new ArrayList();
    }
}
