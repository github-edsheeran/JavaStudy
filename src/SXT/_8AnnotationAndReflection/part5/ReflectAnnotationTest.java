package SXT._8AnnotationAndReflection.part5;

import CoreJavaVolumePartOne.chapter5.part2.Student;
import SXT._8AnnotationAndReflection.part2.MyField;
import SXT._8AnnotationAndReflection.part2.Table;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 进一步了解通过反射操作注解
 * @author: LiuDongMan
 * @createdDate: 2019-09-12 07:54
 **/
public class ReflectAnnotationTest {
    public static void main(String[] args) {
        try {
            Class<Student> aClass = (Class<Student>) Class.forName("SXT._8AnnotationAndReflection.part2.Student");
            Annotation[] annotations = aClass.getDeclaredAnnotations(); // 一个类可以有多个注解

            for (Annotation annotation : annotations) {
//                System.out.println(annotation);
            }

            Table table = aClass.getAnnotation(Table.class);
//            System.out.println(table.value());

            Field[] fields = aClass.getDeclaredFields();

            for (Field field : fields) {
                annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
            }

            // 要想获取指定属性的注解，需要先获得对应的属性对象，就好像要获取类注解，先要获取对应的类对象一样
            Field field = aClass.getDeclaredField("name");
            MyField myField = field.getDeclaredAnnotation(MyField.class);
            System.out.println(myField.columnName() + " --> " + myField.type() + " --> " + myField.length());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
