package SXT._8AnnotationAndReflection.part4;

import SXT._8AnnotationAndReflection.part3.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 通过反射调用构造器、方法、属性等，一般情况下不能操作私有访问权限的元素，但是通过setAccessible(true)可以禁止安全
 * 检查，需要注意的是，禁止安全检查可以提高反射的运行速度
 * @author: LiuDongMan
 * @createdDate: 2019-09-11 10:12
 **/
public class CallConstructorAndMethod {
    public static void main(String[] args) {
        String path = "SXT._8AnnotationAndReflection.part3.User";

        try {
            /**
             * 利用反射调用构造器生成相应的对象
             */
            Class<User> aClass = (Class<User>) Class.forName(path); // 为了避免后续生成对象需要强制转换，这儿加入泛型
            User user = aClass.newInstance();   // 调用类的空构造器，因此，一个类必须要有空构造器

//            System.out.println(user);

            // 获取指定的构造器之后，再传入参数，生成对象
            Constructor<User> constructor = aClass.getDeclaredConstructor(String.class, String.class, int.class);
            user = constructor.newInstance("1001", "张三", 20);

//            System.out.println(user + " --> " + user.getId() + " --> " + user.getName() + " --> " + user.getAge());

            /**
             * 利用反射调用方法
             */
            Method method = aClass.getDeclaredMethod("setName", String.class);
            method.invoke(user, "李四");  // 相当于user.setName("李四");
//            System.out.println(user.getName());

            /**
             * 利用反射操作属性
             */
            Field field = aClass.getDeclaredField("name");
            field.setAccessible(true);
            field.set(user, "王五");  // 不能直接操作类的私有属性，除非忽略访问权限检查
            System.out.println(field.get(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
