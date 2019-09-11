package SXT._8AnnotationAndReflection.part5;

import SXT._8AnnotationAndReflection.part3.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单认识下反射所带来的效率问题
 * @author: LiuDongMan
 * @createdDate: 2019-09-11 11:01
 **/
public class ReflectEfficiencyTest {
    /**
     * 普通方法调用
     */
    public static void test01() {
        User user = new User();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("普通方法调用耗时：" + (endTime - startTime));
    }

    /**
     * 反射调用
     */
    public static void test02() {
        try {
            Class<User> aClass = (Class<User>) Class.forName("SXT._8AnnotationAndReflection.part3.User");
            User user = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("getName");
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000000000; i++) {
                method.invoke(user);
            }

            long endTime = System.currentTimeMillis();

            System.out.println("反射调用耗时：" + (endTime - startTime));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射调用，但是禁止安全检查
     */
    public static void test03() {
        try {
            Class<User> aClass = (Class<User>) Class.forName("SXT._8AnnotationAndReflection.part3.User");
            User user = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("getName");

            method.setAccessible(true);

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000000000; i++) {
                method.invoke(user);
            }

            long endTime = System.currentTimeMillis();

            System.out.println("反射禁止安全检查调用耗时：" + (endTime - startTime));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }
}
