package SXT._8AnnotationAndReflection.part4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

//            System.out.println(aClass.getName());   // 获取包名+类名
//            System.out.println(aClass.getSimpleName()); // 获取类名

            /**
             * 获取属性信息
             * 需要注意的是，未加declared前缀的方法都是只获取public访问权限，类似于私有、保护权限的都不能访问
             */
            Field[] fields = aClass.getFields();
//            System.out.println(fields.length);
            fields = aClass.getDeclaredFields();

//            for (Field field : fields) {
//                System.out.println(field);
//            }

            Field field = aClass.getDeclaredField("name");  // 获取指定的属性，未加declared修饰的只能获取public访问权限相关的内容
//            System.out.println(field);

            /**
             * 获取方法信息
             */
            Method[] methods = aClass.getMethods(); // 获取类中所有的方法，包括自己声明的和继承的

//            for (Method method : methods) {
//                System.out.println(method);
//            }

            methods = aClass.getDeclaredMethods();  // 获取类自己声明的方法

//            for (Method method : methods) {
//                System.out.println(method);
//            }

            Method method = aClass.getMethod("getName", null);  // 获取指定方法信息，第二个参数表示的是方法的形参参数类型
//            System.out.println(method);

            method = aClass.getMethod("setName", String.class);
//            System.out.println(method);

            /**
             * 获取构造器信息
             */
            Constructor[] constructors = aClass.getConstructors();

//            for (Constructor constructor : constructors) {
//                System.out.println(constructor);
//            }

            constructors = aClass.getDeclaredConstructors();
//            for (Constructor constructor : constructors) {
//                System.out.println(constructor);
//            }

            Constructor constructor = aClass.getDeclaredConstructor(null);
//            System.out.println(constructor);

            constructor = aClass.getDeclaredConstructor(String.class, String.class, int.class);
//            System.out.println(constructor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
