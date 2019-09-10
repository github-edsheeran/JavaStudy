package SXT._8AnnotationAndReflection.part4;

import java.lang.reflect.Method;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 根据反射进一步获取对象的相关信息，例如，类名、属性、方法、构造器等
 * @author: LiuDongMan
 * @createdDate: 2019-09-10 14:41
 **/
public class ReflectClassInfo {
    public static void main(String[] args) {
        String path = "SXT._8AnnotationAndReflection.part3.User";

        try {
            Class aClass = Class.forName(path);

            String className = aClass.getName();
            System.out.println(className);

            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName() + " --> " + method.getReturnType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
