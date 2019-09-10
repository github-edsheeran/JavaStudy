package SXT._8AnnotationAndReflection.part1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 自定义一个注解
 * @author: LiuDongMan
 * @createdDate: 2019-09-09 14:45
 **/
@MyAnnotation(name = "Kirito", age = 27, id = "1001")
public class MyAnnotationTest {

    @MyAnnotation(name = "ZhangSan", age = 27, id = "1002", hobby = {"other"})
    public static void test01() {

    }

}

/**
 * 使用自定义注解时，自动实现了java.lang.annotation.Annotation接口
 * 元注解的作用就是负责注解其他注解，java定义了4个标准的meta-annotation类型，它们被用来提供对其他annotation类型做说明，例如：@Target、
 * @Retention、@Documented、@Inherited，前面两个用的是最多的
 * @Target: 描述注解的使用范围
 * @Retention: 表示需要在什么级别保存该注释信息，用于描述注解的声明周期，其中，需要注意的是RetentionPolicy.RUNTIME，这个可以被反射机制读取
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 如果只有一个属性的话，推荐属性名为value

    // 注解的默认值
    String name() default "";

    int age();

    String id();

    String[] hobby() default {"basketball", "computer"};
}
