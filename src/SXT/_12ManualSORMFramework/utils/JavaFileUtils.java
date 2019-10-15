package SXT._12ManualSORMFramework.utils;

import SXT._12ManualSORMFramework.bean.ColumnInfo;
import SXT._12ManualSORMFramework.bean.JavaFieldGetSet;
import SXT._12ManualSORMFramework.bean.TableInfo;
import SXT._12ManualSORMFramework.core.DBManager;
import SXT._12ManualSORMFramework.core.MySQLTypeConvertor;
import SXT._12ManualSORMFramework.core.TableContext;
import SXT._12ManualSORMFramework.core.TypeConvertor;
import SXT._8AnnotationAndReflection.part2.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装了生成Java文件(源代码)常用的操作
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:58
 **/
public class JavaFileUtils {
    /**
     * 根据字段信息生成java属性以及相应的get和set方法信息
     * @param columnInfo 字段信息
     * @param convertor 类型转换器
     * @return 封装了java属性以及对应的get和set方法信息的对象
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor convertor) {
        // 获取数据库字段属性所对应的java属性类型
        String javaFieldType = convertor.databaseTypeToJavaType(columnInfo.getDataType());
        // 生成属性信息
        String fieldInfo = new StringBuilder().append("\tprivate ").append(javaFieldType).append(" ").append(columnInfo.getName()).append(";\n").toString();
        // 生成属性对应的get方法信息
        String getInfo = new StringBuilder("\tpublic ").append(javaFieldType).append(" ").append("get").append(StringUtils.firstCharToUppercase(columnInfo.getName()))
                .append("() {\n\t\treturn ").append(columnInfo.getName()).append(";\n\t}\n\n").toString();
        // 生成属性对应的set方法信息
        String setInfo = new StringBuilder("\tpublic void set").append(StringUtils.firstCharToUppercase(columnInfo.getName())).append("(").append(javaFieldType)
                .append(" ").append(columnInfo.getName()).append(") {\n\t\tthis.").append(columnInfo.getName()).append(" = ").append(columnInfo.getName())
                .append(";\n\t}\n\n").toString();
        JavaFieldGetSet jfgs = new JavaFieldGetSet(fieldInfo, getInfo, setInfo);

        return jfgs;
    }

    /**
     * 根据表信息生成java类的源代码
     * @param tableInfo 表信息
     * @param convertor 类型转换器
     * @return 封装了java类的源代码
     */
    public static String createJavaSRC(TableInfo tableInfo, TypeConvertor convertor) {
        Map<String, ColumnInfo> columnInfoMap = tableInfo.getColumns();
        List<JavaFieldGetSet> jfgsList = new ArrayList<>();

        for (ColumnInfo columnInfo : columnInfoMap.values()) {
            jfgsList.add(createFieldGetSetSRC(columnInfo, convertor));
        }

        // 生成package、import以及类声明语句
        StringBuilder srcBuilder = new StringBuilder("package ").append(DBManager.getConf().getPoPackage()).append(";\n\n").
                append("import java.sql.*;\nimport java.util.*;\n\n").append("public class ").
                append(StringUtils.firstCharToUppercase(tableInfo.getName())).append(" {\n");

        // 生成属性信息
        for (JavaFieldGetSet javaFieldGetSet : jfgsList) {
            srcBuilder.append(javaFieldGetSet.getFieldInfo());
        }

        srcBuilder.append("\n");

        // 生成get和set方法信息
        for (JavaFieldGetSet javaFieldGetSet : jfgsList) {
            srcBuilder.append(javaFieldGetSet.getGetInfo()).append(javaFieldGetSet.getSetInfo());
        }

        // 生成类结束
        srcBuilder.append("}");

        return srcBuilder.toString();
    }

    /**
     * 根据数据库表信息创建相应的类文件
     * @param tableInfo 数据库表信息
     * @param convertor 类型转换器
     */
    public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor convertor) {
        String src = createJavaSRC(tableInfo, convertor);
        String srcPath = DBManager.getConf().getSrcPath();
        String poPackagePath = DBManager.getConf().getPoPackage().replace('.', '\\');
        // 由于后面有判断文件目录相关的代码，因此，这个地方不能带上完整的文件目录名，否则会以文件名创建相应的目录
        File file = new File(new StringBuilder(srcPath).append("\\").append(poPackagePath).toString());

        // 判断文件目录是否存在，如果不存在，则创建
        if (!file.exists()) {
            file.mkdirs();
        }

        // 利用IO流创建对应的源代码文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new StringBuilder(file.getAbsolutePath()).append("\\").
                append(StringUtils.firstCharToUppercase(tableInfo.getName())).append(".java").toString()))) {
            writer.write(src);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ColumnInfo columnInfo = new ColumnInfo("userName", "int", 0);
//        JavaFieldGetSet jfgs = createFieldGetSetSRC(columnInfo, new MySQLTypeConvertor());
//
//        System.out.println(jfgs);

        Map<String, TableInfo> map = TableContext.tables;
    }
}
