package SXT._8AnnotationAndReflection.part5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类属性在数据库中对应的字段的信息
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface MyField {
    String columnName();    // 字段名

    String type();  // 字段类型

    int length();   // 字段长度
}
