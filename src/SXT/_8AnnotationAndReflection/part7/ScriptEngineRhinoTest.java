package SXT._8AnnotationAndReflection.part7;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解java中的脚本引擎
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

            // 导入其他java包，使用其他包中的java类

        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
