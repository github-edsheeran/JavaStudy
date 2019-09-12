package SXT._8AnnotationAndReflection.part2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 使用反射读取注解的信息，模拟处理注解信息的流程
 * @author: LiuDongMan
 * @createdDate: 2019-09-09 15:43
 **/
public class ReflectAnnotationTest {
    public static void main(String[] args) {
        try {
            Class aClass = Class.forName("SXT._8AnnotationAndReflection.part2.Student");
            Annotation[] annotations = aClass.getAnnotations(); // 获得和类相关的所有的有效注解，注意，只是类相关的

            for (Annotation a : annotations) {
                System.out.println(a);
            }

            Table table = (Table) aClass.getAnnotation(Table.class);    // 根据类对象获取类的指定的注解
            System.out.println(table.value());

            /**
             * 根据属性名，获取类的指定属性的注解
             * 注意：这个地方是系统自带的那个类，而不是自己定义的
             */
            Field field = aClass.getDeclaredField("name");
            MyField myField = field.getAnnotation(MyField.class);
            System.out.println(myField.columnName() + " --> " + myField.type() + " --> " + myField.length());

            // 获得类的所有属性的注解
            Field[] fields = aClass.getDeclaredFields();

            for (Field f : fields) {
                MyField tempField = f.getAnnotation(MyField.class);
                System.out.println(tempField.columnName() + " --> " + tempField.type() + " --> " + tempField.length());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
