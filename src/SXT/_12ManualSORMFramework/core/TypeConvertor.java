package SXT._12ManualSORMFramework.core;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责Java数据类型和数据库数据类型的互相转换
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:52
 **/
public interface TypeConvertor {
    /**
     * 将数据库数据类型转换为Java的数据类型
     * @param columnType 数据库数据类型
     * @return 返回Java的数据类型
     */
    String databaseTypeToJavaType(String columnType);

    /**
     * 将Java数据类型转换为数据库数据类型
     * @param javaType Java数据类型
     * @return 返回数据库数据类型
     */
    String javaTypeToDatabaseType(String javaType);
}
