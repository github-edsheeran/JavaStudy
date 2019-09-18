package SXT._8AnnotationAndReflection.part8;

import javassist.*;

import java.io.IOException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解java中的字节码操作的类库javassist
 * @author: LiuDongMan
 * @createdDate: 2019-09-18 09:53
 **/
public class JavassistTest {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("Employee");   // 注意：这个地方如果加上包名会创建相应的文件夹

        try {
            // 创建属性
            CtField field01 = CtField.make("private String id;", ctClass);
            CtField field02 = CtField.make("private String name;", ctClass);
            CtField field03 = CtField.make("private int age;", ctClass);

            ctClass.addField(field01);
            ctClass.addField(field02);
            ctClass.addField(field03);

            // 创建方法
            CtMethod method01 = CtMethod.make("public String getId() {return this.id;}", ctClass);
            CtMethod method02 = CtMethod.make("public void setId(String id) {this.id = id;}", ctClass);

            ctClass.addMethod(method01);
            ctClass.addMethod(method02);

            // 创建构造器，如果是空构造器，第一个数组参数不写内容即可
            CtConstructor constructor01 = new CtConstructor(new CtClass[]{}, ctClass);
            CtConstructor constructor02 = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), pool.get("java.lang.String"), CtClass.intType}, ctClass);
            constructor02.setBody("{$0.id = $1; $0.name = $2; $0.age = $3;}");  // $0代表this；$1、$2、$3代表形参，如果直接用this.id = id的形式，构造器方法体会有问题

            ctClass.addConstructor(constructor01);
            ctClass.addConstructor(constructor02);

            ctClass.writeFile("d:/WorkSpace/IdeaWorkSpace/JavaStudy/src/SXT/_8AnnotationAndReflection/part8");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
