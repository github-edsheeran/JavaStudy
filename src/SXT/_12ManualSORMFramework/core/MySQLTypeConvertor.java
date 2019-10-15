package SXT._12ManualSORMFramework.core;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: MySQL数据类型和java数据类型的转换
 * @author: LiuDongMan
 * @createdDate: 2019-10-14 10:11
 **/
public class MySQLTypeConvertor implements TypeConvertor {
    @Override
    public String databaseTypeToJavaType(String columnType) {
        if ("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("int".equalsIgnoreCase(columnType) || "tinyint".equalsIgnoreCase(columnType)
                || "smallint".equalsIgnoreCase(columnType)
                || "integer".equalsIgnoreCase(columnType)
        ) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        } else if ("double".equalsIgnoreCase(columnType) || "float".equalsIgnoreCase(columnType)) {
            return "Double";
        } else if ("clob".equalsIgnoreCase(columnType)) {
            return "java.sql.CLob";
        } else if ("blob".equalsIgnoreCase(columnType)) {
            return "java.sql.BLob";
        } else if ("date".equalsIgnoreCase(columnType)) {
            return "java.sql.Date";
        } else if ("time".equalsIgnoreCase(columnType)) {
            return "java.sql.Time";
        } else if ("timestamp".equalsIgnoreCase(columnType)) {
            return "java.sql.Timestamp";
        }

        return null;
    }

    @Override
    public String javaTypeToDatabaseType(String javaType) {
        return null;
    }
}
