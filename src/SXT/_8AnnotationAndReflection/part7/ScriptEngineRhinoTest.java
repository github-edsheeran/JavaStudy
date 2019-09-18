package SXT._8AnnotationAndReflection.part7;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解java中的脚本引擎
 * 由于日常使用较少，了解即可，如果想深入了解的话，可以详细学习Rhino的语法
 * @author: LiuDongMan
 * @createdDate: 2019-09-12 14:45
 **/
public class ScriptEngineRhinoTest {
    public static void main(String[] args) {
        // 获取脚本引擎管理对象
        ScriptEngineManager sem = new ScriptEngineManager();
        // 根据脚本引擎管理对象获取指定的脚本引擎对象
        ScriptEngine engine = sem.getEngineByName("javascript");
        String jsCode = "var user = {name : '张三', age : 20, schools : ['北京大学', '清华大学']};" +
                        "print(user.name);";

        try {
            // 不管什么js代码，都需要先通过eval方法存储到引擎上下文中
            engine.eval(jsCode);
            engine.put("msg", "Hello, world!");
            engine.eval("msg = 'Hello, javascript'");

            System.out.println(engine.get("msg"));

            jsCode = "function add(a, b) {var sum = a + b; return sum;}";
            engine.eval(jsCode);    // 要执行函数，需要先存储到引擎的上下文中
            // 执行脚本中定义的方法
            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("add", new Object[]{2, 3});

            System.out.println(result);

            // 导入其他java包，使用其他包中的java类（需要注意的是，jdk1.6中，是通过importPackage(java.util)进行的导入操作，新的版本直接引用即可）
            jsCode = "var list = java.util.Arrays.asList([\"北京大学\", \"清华大学\"]);";
            engine.eval(jsCode);
            List<String> list = (List<String>) engine.get("list");

            for (String s : list) {
                System.out.println(s);
            }

            // 执行一个js文件，如果要获得子文件夹内的文件，需要完整的路径
            URL url = ScriptEngineRhinoTest.class.getClassLoader().getResource("SXT/_8AnnotationAndReflection/part7/test.js");
            engine.eval(new FileReader(url.getPath()));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
