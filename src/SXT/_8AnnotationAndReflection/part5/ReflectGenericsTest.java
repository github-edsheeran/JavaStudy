package SXT._8AnnotationAndReflection.part5;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 通过反射操作泛型
 * Java采用泛型擦除的机制来引入泛型，java中的泛型仅仅是给编译器javac使用的，确保数据的安全性和免去强制类型转换的麻烦，但是，一旦编译完成，所有
 * 的和泛型有关的类型全部擦除
 * 为了通过反射操作这些类型以迎合实际开发的需要，java就新增了ParameterizedType、GenericArrayType、TypeVariable和WildcardType几种类型
 * 来代表不能被归一到Class类中的类型但是又和原始类型齐名的类型
 * 1.ParameterizedType: 表示一种参数化的类型，比如Collection<String>
 * 2.GenericArrayType: 表示一种元素类型是参数化类型或者类型变量的数组类型
 * 3.TypeVariable: 是各种类型变量的公共父接口
 * 4.WildcardType: 代表一种通配符类型表达式，例如?，? extends Number，? super Integer
 * @author: LiuDongMan
 * @createdDate: 2019-09-11 11:18
 **/
public class ReflectGenericsTest {
    public void test01(Map<String, Integer> map, List<Date> list) {
        System.out.println("反射形参的泛型");
    }

    public Map<Character, Double> test02() {
        System.out.println("反射操作返回参数的泛型");
        return null;
    }

    public static void main(String[] args) {
        try {
            // 获取类对象
            Class<ReflectGenericsTest> aClass = (Class<ReflectGenericsTest>) Class.forName("SXT._8AnnotationAndReflection.part5.ReflectGenericsTest");
            // 获取指定方法信息
            Method method = aClass.getDeclaredMethod("test01", Map.class, List.class);
            // 根据指定方法获取泛型参数信息
            Type[] types = method.getGenericParameterTypes();

            for (Type type : types) {
//                System.out.println("#" + type);

                if (type instanceof ParameterizedType) {
                    // 根据泛型参数获取参数里面的实际泛型类型
                    Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();

                    for (Type genericType : genericTypes) {
//                        System.out.println("泛型类型：" + genericType);
                    }
                }
            }

            // 根据指定方法获取泛型返回参数信息
            method = aClass.getDeclaredMethod("test02");
            Type type = method.getGenericReturnType();

            if (type instanceof ParameterizedType) {
                // 根据泛型参数获取参数里面的实际泛型类型
                Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();

                for (Type genericType : genericTypes) {
                    System.out.println("泛型类型：" + genericType);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
