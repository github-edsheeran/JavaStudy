package SXT._12ManualSORMFramework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装了反射常用的操作
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:57
 **/
public class ReflectUtils {
    /**
     * 调用指定对象的对应属性的get方法
     * @param aClass 指定对象所属的类对象
     * @param fieldName 属性名
     * @param object 指定对象
     * @return 调用get方法之后返回的内容
     */
    public static Object invokeGet(Class aClass, String fieldName, Object object) {
        try {
            Method method = aClass.getMethod("get" + StringUtils.firstCharToUppercase(fieldName), null);
            return method.invoke(object, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
