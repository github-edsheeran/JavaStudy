package SXT._8AnnotationAndReflection.part8;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试javassist的api
 * @author: LiuDongMan
 * @createdDate: 2019-09-19 08:27
 **/
public class JavassistAPITest {
    /**
     * 处理类的基本用法
     * @throws NotFoundException
     */
    public static void test01() {
        ClassPool pool = ClassPool.getDefault();

        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            byte[] ctClassBytes = ctClass.toBytecode(); // 获取类的字节数组

            System.out.println(Arrays.toString(ctClassBytes));
            System.out.println(ctClass.getName());  // 获取完整类名
            System.out.println(ctClass.getSimpleName());    // 获取简要类名
            System.out.println(ctClass.getSuperclass());    // 获取父类

            for (CtClass anInterface : ctClass.getInterfaces()) {   // 获取所有的接口
                System.out.println(anInterface);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试产生新的方法
     */
    public static void test02() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            CtMethod method = CtNewMethod.make("public int add(int a, int b) {return a + b;}", ctClass);    // 单独以这种方式新增方法的话，形参没有问题
            method = new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType, CtClass.intType}, ctClass);

//            method.setBody("{return a + b;}");  // 以这种方式新增方法的话，需要使用站位符指定对应的形参，详细的可以看文档
            method.setBody("{return $1 + $2;}");
            method.setModifiers(Modifier.PUBLIC);   // 声明新增方法的访问修饰符
            ctClass.addMethod(method);

            // 通过反射调用新增的方法
            Class aClass = ctClass.toClass();   // Javassist中生成的类可以直接调用toClass方法转换为Class的类对象
            Object object = aClass.newInstance();
            Method classMethod = aClass.getDeclaredMethod("add", int.class, int.class);
            Object result = classMethod.invoke(object, 200, 300);

            System.out.println(result);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过javassist实现切面编程
     */
    public static void test03() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            CtMethod method = ctClass.getDeclaredMethod("sayHello", new CtClass[]{CtClass.intType});

            method.insertBefore("System.out.println(\"Start\");");  // 插入到方法体之前
            method.insertAfter("System.out.println(\"End\");"); // 插入到方法体之后
            method.insertAt(34, "System.out.println(\"I'm here!\");");  // 插入到指定的行，原来的行往后移

            Class aClass = ctClass.toClass();
            Object object = aClass.newInstance();
            Method classMethod = aClass.getDeclaredMethod("sayHello", int.class);

            classMethod.invoke(object, 20);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用javassist对属性进行相关操作
     */
    public static void test04() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            // 两种生成属性的方式
            CtField field = CtField.make("private int studentNum;", ctClass);
            field = new CtField(CtClass.intType, "studentNum", ctClass);    // 这个地方不知道为什么pool.get("java.lang.String")会报错

            field.setModifiers(Modifier.PRIVATE);
            ctClass.addField(field, "1001");
            ctClass.addMethod(CtNewMethod.getter("getStudentNum", field));  // Javassist中，另一种新增方法的方式

            Class aClass = ctClass.toClass();
            Object object = aClass.newInstance();
            Method classMethod = aClass.getDeclaredMethod("getStudentNum");
            Object result = classMethod.invoke(object);

            System.out.println(result);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过javassist操作构造器
     */
    public static void test05() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            CtConstructor constructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), pool.get("java.lang.String")}, ctClass);

            constructor.setBody("{$0.id = $1; $0.name = $2;}");
            ctClass.addConstructor(constructor);

            CtConstructor[] constructors = ctClass.getConstructors();

            for (CtConstructor temp : constructors) {
                System.out.println(temp.getLongName());
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过javassist操作注解，目前来说，除非使用javassist的底层类，否则，注解只能进行读取操作
     */
    public static void test06() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("SXT._8AnnotationAndReflection.part8.Student");
            Object[] objects = ctClass.getAnnotations();
            Author author= (Author) objects[0]; // 在知道类注解的情况下，直接通过数组下标取值

            System.out.println(author.name() + " --> " + author.year());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        test01();
//        test02();
//        test03();
//        test04();
//        test05();
        test06();
    }
}
