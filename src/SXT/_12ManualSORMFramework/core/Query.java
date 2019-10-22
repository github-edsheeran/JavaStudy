package SXT._12ManualSORMFramework.core;

import SXT._12ManualSORMFramework.bean.ColumnInfo;
import SXT._12ManualSORMFramework.bean.TableInfo;
import SXT._12ManualSORMFramework.po.Emp;
import SXT._12ManualSORMFramework.utils.JDBCUtils;
import SXT._12ManualSORMFramework.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责数据库的DML操作(对外提供服务的核心接口)
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:19
 **/
public abstract class Query {
    public static void main(String[] args) {
//        List<Emp> list = new MySQLQuery().queryRows("SELECT * FROM emp WHERE id > ?", Emp.class, new Object[]{3});
//
//        for (Emp emp : list) {
//            System.out.println(emp.getId() + " --> " + emp.getEmpName() + " --> " + emp.getPassword());
//        }

        System.out.println(new MySQLQuery().queryValue("SELECT empName FROM emp WHERE id = ?", new Object[]{4}));
    }

    /**
     * 采用模板方法模式将JDBC操作封装成模板，便于重用
     * @param sql SQL语句
     * @param params SQL参数
     * @param aClass 要封装到的java类对象
     * @param back CallBack的实现类，起到回调的作用
     * @return
     */
    public Object executeQueryTemplate(String sql, Object[] params, Class aClass, CallBack back) {
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object object = null;

        try {
            ps = conn.prepareStatement(sql);

            JDBCUtils.handleParams(ps, params);

            rs = ps.executeQuery();

            object = back.doQuery(conn, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * 直接执行一个DML语句
     * @param sql SQL语句
     * @param params 对应的参数
     * @return 返回执行SQL语句后影响的记录行数
     */
    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            ps = conn.prepareCall(sql);

            JDBCUtils.handleParams(ps, params);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, ps, conn);
        }

        return result;
    }

    /**
     * 将一个对象存储到数据库中
     * @param obj 要存储的对象
     */
    public void insert(Object obj) {
        Class aClass = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        Map<String, ColumnInfo> columnInfoMap = tableInfo.getColumns();
        List<Object> paramList = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ").append(tableInfo.getName()).append(" (");

        // 自己写的插入代码
//        for (ColumnInfo columnInfo : columnInfoMap.values()) {
//            String columnName = columnInfo.getName();
//            Object param = ReflectUtils.invokeGet(aClass, columnName, obj);
//
//            if (param != null) {
//                sqlBuilder.append(columnName).append(", ");
//                paramList.add(param);
//            }
//        }
//
//        sqlBuilder.delete(sqlBuilder.lastIndexOf(","), sqlBuilder.length()).append(") VALUES (");
//
//        for (int i = 0; i < paramList.size(); i++) {
//            sqlBuilder.append("?, ");
//        }
//
//        sqlBuilder.delete(sqlBuilder.lastIndexOf(","), sqlBuilder.length()).append(")");

        // 视频中的插入代码
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = ReflectUtils.invokeGet(aClass, fieldName, obj);

            // 判断属性值是否为空，如果为空，则不进行插入操作
            if (fieldValue != null) {
                sqlBuilder.append(fieldName).append(", ");
                paramList.add(fieldValue);
            }
        }

        sqlBuilder.setCharAt(sqlBuilder.lastIndexOf(","), ')');
        sqlBuilder.append("VALUES (");

        for (int i = 0; i < paramList.size(); i++) {
            sqlBuilder.append("?,");
        }

        sqlBuilder.setCharAt(sqlBuilder.length() - 1, ')');

        executeDML(sqlBuilder.toString(), paramList.toArray());
    }

    /**
     * 根据主键id删除Class类对象所对应的数据库表中的记录
     * @param aClass 数据库表所对应的Class类对象
     * @param id 主键的值
     */
    public void delete(Class aClass, Object id) {
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPrimaryKeyColumn = tableInfo.getOnlyPrimaryKey();
        String onlyPrimaryKey = onlyPrimaryKeyColumn.getName();
        String sql = new StringBuilder("DELETE FROM ").append(tableInfo.getName()).append(" WHERE ").append(onlyPrimaryKey).append(" = ?").toString();

        executeDML(sql, new Object[]{id});
    }

    /**
     * 删除对象所对应的数据库表中的记录
     * @param obj
     */
    public void delete(Object obj) {
        Class aClass = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPrimaryKeyColumn = tableInfo.getOnlyPrimaryKey();
        String onlyPrimaryKey = onlyPrimaryKeyColumn.getName();
        Object id = ReflectUtils.invokeGet(aClass, onlyPrimaryKey, obj);

        delete(aClass, id);
    }

    /**
     * 更新对象所对应的数据库表中的记录，并且只更新指定的字段
     * @param obj 需要更新的对象
     * @param fieldNames 指定的字段名
     * @return 返回执行SQL语句后影响的记录行数
     */
    public int update(Object obj, String[] fieldNames) {
        Class aClass = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        List<Object> paramList = new ArrayList<>(fieldNames.length);
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ").append(tableInfo.getName()).append(" SET ");

        for (String fieldName : fieldNames) {
            Object fieldValue = ReflectUtils.invokeGet(aClass, fieldName, obj);

            paramList.add(fieldValue);
            sqlBuilder.append(fieldName).append(" = ?, ");
        }

        sqlBuilder.delete(sqlBuilder.lastIndexOf(","), sqlBuilder.length()).append(" WHERE ").append(tableInfo.getOnlyPrimaryKey().getName()).append(" = ?");
        paramList.add(ReflectUtils.invokeGet(aClass, tableInfo.getOnlyPrimaryKey().getName(), obj));

        return executeDML(sqlBuilder.toString(), paramList.toArray());
    }

    /**
     * 查询返回多条记录，并将每行记录封装到数据库表对应的Class生成的对象中
     * @param sql 查询语句
     * @param aClass 数据库表所对应的Class对象
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    public List queryRows(String sql, Class aClass, Object[] params) {
        return (List) executeQueryTemplate(sql, params, aClass, new CallBack() {
            @Override
            public Object doQuery(Connection conn, PreparedStatement ps, ResultSet rs) {
                List list = new ArrayList();
                try {
                    ResultSetMetaData metaData = rs.getMetaData();

                    while (rs.next()) {
                        Object rowObj = aClass.newInstance();

                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            String columnName = metaData.getColumnLabel(i + 1);
                            Object columnValue = rs.getObject(i + 1);

                            ReflectUtils.invokeSet(rowObj, columnName, columnValue);
                        }

                        list.add(rowObj);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                return list;
            }
        });

//        Connection conn = DBManager.getConnection();
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        ResultSetMetaData metaData = null;
//        List list = new ArrayList();
//
//        try {
//            ps = conn.prepareStatement(sql);
//
//            JDBCUtils.handleParams(ps, params);
//
//            rs = ps.executeQuery();
//
//
//
//            metaData = ps.getMetaData();    // 获取数据库表相关的元数据
//
//            while (rs.next()) {
//                Object rowObj = aClass.newInstance();   // 根据类对象创建相应的对象
//
//                for (int i = 0; i < metaData.getColumnCount(); i++) {
//                    String columnName = metaData.getColumnLabel(i + 1);  // JDBC中的所有索引都是从1开始的
//                    Object columnValue = rs.getObject(i + 1);
//
//                    ReflectUtils.invokeSet(rowObj, columnName, columnValue);
//                }
//
//                list.add(rowObj);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.close(rs, ps, conn);
//        }
//
//        return list;
    }

    /**
     * 查询返回一条记录，并将每行记录封装到数据库表对应的Class生成的对象中
     * @param sql 查询语句
     * @param aClass 数据库表所对应的Class对象
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    public Object queryUniqueRow(String sql, Class aClass, Object[] params) {
        List list = queryRows(sql, aClass, params);

        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    /**
     * 查询返回一个值，即一行一列，并将该值返回
     * @param sql 查询语句
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    public Object queryValue(String sql, Object[] params) {
        return executeQueryTemplate(sql, params, null, new CallBack() {
            @Override
            public Object doQuery(Connection conn, PreparedStatement ps, ResultSet rs) {
                Object object = null;

                try {
                    while (rs.next()) {
                        object = rs.getObject(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return object;
            }
        });
//        Connection conn = DBManager.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Object object = null;
//
//        try {
//            ps = conn.prepareStatement(sql);
//
//            JDBCUtils.handleParams(ps, params);
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                object = rs.getObject(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.close(rs, ps, conn);
//        }
//
//        return object;
    }

    /**
     * 查询返回一个数字，即一行一列，并将该值返回，由于Number是所有数字包装类的父类，因此，作为返回值的参数类型
     * @param sql 查询语句
     * @param params SQL语句的参数
     * @return 返回查询到的结果
     */
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }
}
