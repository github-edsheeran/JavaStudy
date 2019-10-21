package SXT._12ManualSORMFramework.core;

import SXT._12ManualSORMFramework.bean.ColumnInfo;
import SXT._12ManualSORMFramework.bean.TableInfo;
import SXT._12ManualSORMFramework.po.Emp;
import SXT._12ManualSORMFramework.utils.JDBCUtils;
import SXT._12ManualSORMFramework.utils.ReflectUtils;
import SXT._12ManualSORMFramework.vo.EmpVO;
import SXT._8AnnotationAndReflection.part2.Table;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 负责针对mySQL数据库的相关操作
 * @author: LiuDongMan
 * @createdDate: 2019-10-16 09:49
 **/
public class MySQLQuery implements Query {
    public static void main(String[] args) {
//        Emp emp = new Emp();
//
//        emp.setId(40);
//        emp.setEmpName("田七");
//        emp.setPassword("123456");
//
//        new MySQLQuery().update(emp, new String[]{"empName"});

//        String sql = "SELECT * FROM EMP WHERE ID = ?";
//        Emp emp = (Emp) new MySQLQuery().queryUniqueRow(sql, Emp.class, new Object[]{3});
//
//
//        System.out.println(emp.getId() + " --> " + emp.getEmpName() + " --> " + emp.getPassword());

//        String sql = "SELECT A.id, A.empName, A.salary + A.bonus 'totalSalary', B.deptName, B.address FROM emp A LEFT JOIN dept B ON A.deptID = B.id";
//        List<EmpVO> list = new MySQLQuery().queryRows(sql, EmpVO.class, null);
//
//        for (EmpVO empVO : list) {
//            System.out.println(empVO.getId() + " --> " + empVO.getEmpName() + " --> " + empVO.getTotalSalary() + " --> " + empVO.getDeptName() + " --> " + empVO.getAddress());
//        }

        String sql = "SELECT empName FROM emp WHERE id = ?";
        System.out.println(new MySQLQuery().queryValue(sql, new Object[]{2}));
    }

    @Override
    public int executeDML(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            ps = conn.prepareCall(sql);

            JDBCUtils.handleParams(ps, params);
            System.out.println("执行的SQL语句 --> " + ps.toString());
//            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, ps, conn);
        }

        return result;
    }

    @Override
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

    @Override
    public void delete(Class aClass, Object id) {
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPrimaryKeyColumn = tableInfo.getOnlyPrimaryKey();
        String onlyPrimaryKey = onlyPrimaryKeyColumn.getName();
        String sql = new StringBuilder("DELETE FROM ").append(tableInfo.getName()).append(" WHERE ").append(onlyPrimaryKey).append(" = ?").toString();

        executeDML(sql, new Object[]{id});
    }

    @Override
    public void delete(Object obj) {
        Class aClass = obj.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPrimaryKeyColumn = tableInfo.getOnlyPrimaryKey();
        String onlyPrimaryKey = onlyPrimaryKeyColumn.getName();
        Object id = ReflectUtils.invokeGet(aClass, onlyPrimaryKey, obj);

        delete(aClass, id);
    }

    @Override
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

    @Override
    public List queryRows(String sql, Class aClass, Object[] params) {
        Connection conn = DBManager.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        ResultSetMetaData metaData = null;
        List list = new ArrayList();

        try {
            ps = conn.prepareStatement(sql);

            JDBCUtils.handleParams(ps, params);

            rs = ps.executeQuery();
            metaData = ps.getMetaData();    // 获取数据库表相关的元数据

            while (rs.next()) {
                Object rowObj = aClass.newInstance();   // 根据类对象创建相应的对象

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i + 1);  // JDBC中的所有索引都是从1开始的
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
        } finally {
            DBManager.close(rs, ps, conn);
        }

        return list;
    }

    @Override
    public Object queryUniqueRow(String sql, Class aClass, Object[] params) {
        List list = queryRows(sql, aClass, params);

        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public Object queryValue(String sql, Object[] params) {
        Connection conn = DBManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object object = null;

        try {
            ps = conn.prepareStatement(sql);

            JDBCUtils.handleParams(ps, params);

            rs = ps.executeQuery();

            while (rs.next()) {
                object = rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, ps, conn);
        }

        return object;
    }

    @Override
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }
}
