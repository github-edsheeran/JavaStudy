package SXT._12ManualSORMFramework.core;

import SXT._12ManualSORMFramework.bean.ColumnInfo;
import SXT._12ManualSORMFramework.bean.TableInfo;
import SXT._12ManualSORMFramework.utils.JavaFileUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责获取和管理数据库所有表结构和类结构的关系，并可以通过表结构生成类结构
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:54
 **/
public class TableContext {
    /**
     * 表名为key，表信息对象为value
     */
    public static Map<String, TableInfo> tables = new HashMap<>();

    /**
     * 将po的class对象和表信息对象关联起来，便于重用！
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<>();

    private TableContext() {
    }

    static {
        try {
            // 初始化获得表的信息，在mySQL高版本中会出现获取所有数据库表的情况，这个时候需要在jdbcURL中加入nullCatalogMeansCurrent=true
            Connection con = DBManager.getConnection();
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet tableRet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");
                TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(), new HashMap<String, ColumnInfo>());

                tables.put(tableName, ti);

                ResultSet set = dbmd.getColumns(null, "%", tableName, "%");  // 查询表中的所有字段

                while (set.next()) {
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),
                            set.getString("TYPE_NAME"), 0);
                    ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
                }

                ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);  // 查询t_user表中的主键

                while (set2.next()) {
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
                    ci2.setKeyType(1);  // 设置为主键类型
                    ti.getPrimaryKeys().add(ci2);
                }

                if (ti.getPrimaryKeys().size() > 0) {  // 取唯一主键，方便使用，如果是联合主键，则为空
                    ti.setOnlyPrimaryKey(ti.getPrimaryKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 当框架启动的时候就更新类结构
        updateJavaPOFile();
//
//        // 加载po包下面所有的类，便于重用，提高效率！
//        loadPOTables();
    }

    /**
     * 根据表结构，更新配置的po包下面的java类
     * 实现了从表结构转化到类结构
     */
    public static void updateJavaPOFile() {
        Map<String, TableInfo> map = TableContext.tables;

        for (TableInfo t : map.values()) {
            JavaFileUtils.createJavaPOFile(t, new MySQLTypeConvertor());
        }
    }
//
//    /**
//     * 加载po包下面的类
//     */
//    public static void loadPOTables(){
//
//        for(TableInfo tableInfo:tables.values()){
//            try {
//                Class c = Class.forName(DBManager.getConf().getPoPackage()
//                        +"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
//                poClassTableMap.put(c, tableInfo);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }


    public static void main(String[] args) {
        Map<String, TableInfo> tables = TableContext.tables;

        System.out.println(tables);
    }
}
