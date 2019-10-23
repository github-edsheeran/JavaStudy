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
            Method method = aClass.getDeclaredMethod("get" + StringUtils.firstCharToUppercase(fieldName), null);
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

    /**
     * 调用指定对象的对应属性的set方法
     * @param object 指定的对象
     * @param columnName 属性所对应的列名
     * @param columnValue 属性所对应的数据库表中字段的值
     */
    public static void invokeSet(Object object, String columnName, Object columnValue) {
        try {
            // 对于数据库字段值为空的情况，不去调用相应的set方法
            if (columnValue != null) {
                // 第二个参数的参数类型即数据库查询出来返回值的类型，例如，查询出来的值类型为java.lang.String，这儿的参数类型也是这个
                Method method = object.getClass().getDeclaredMethod("set" + StringUtils.firstCharToUppercase(columnName), columnValue.getClass());
                method.invoke(object, columnValue);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
