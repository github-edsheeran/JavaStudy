package SXT._8AnnotationAndReflection.part6;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解java中的动态编译
 * 常见的应用场景：
 * 1.浏览器编写java代码，上传到服务器编译和运行的在线评测系统
 * 2.服务器动态加载某些类文件进行编译
 * 现在的话了解即可，因为使用的较少，不过需要注意的是，动态编译类的时候，类所在的包需要做额外的处理才行，否则会出问题，网上的方法是命令行中加入
 * 参数-d
 * @author: LiuDongMan
 * @createdDate: 2019-09-12 08:22
 **/
public class DynamicCompileTest {
    public static void main(String[] args) {
        // 如果想对字符串进行动态编译，通过io流存储为文件之后再进行操作即可
        String str = "public class Test { public static void main(String[] args) { System.out.println(\"Hello, world!\"); } }";
        // 获取java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 这个地方执行之后会生成编译文件，因此先注释掉
        int result = compiler.run(null, null, null, "D:/MyJava/HelloWorld.java");
        System.out.println(result == 0 ? "编译成功" : "编译失败");

        /**
         * 两种方式运行编译好的类：
         * 1.通过Runtime；
         * 2.通过反射，如果是要反射之前项目中不存在的类，好像需要使用到URL才行；
         */
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process process = runtime.exec("java -cp D:/MyJava HelloWorld");
//            BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getInputStream())));
//            String msg = null;
//
//            while ((msg = br.readLine()) != null) {
//                System.out.println(msg);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            // 这个地方结尾必须加上/符号，否则会报ClassNotFoundException异常
            URL[] urls = new URL[] {new URL("file:/D:/MyJava/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class aClass = loader.loadClass("HelloWorld");
            Method method = aClass.getDeclaredMethod("main", String[].class);

            /**
             * 调用加载类的main方法
             * 由于是静态方法，因此第一个参数，即指定的调用对象赋值为null；
             * 第二个参数必须加上强制转型，因为不加的话，会编译成method.invoke(null, "aa", "bb", "cc")，就发生了参数不匹配的问题
             */
            method.invoke(null, (Object) new String[]{"aa", "bb", "cc"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
