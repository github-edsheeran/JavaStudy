package SXT._12ManualSORMFramework.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 封装了JDBC常用的操作
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:56
 **/
public class JDBCUtils {
    /**
     * 给SQL语句设置参数
     * @param ps 预编译SQL语句对象
     * @param params 参数
     */
    public static void handleParams(PreparedStatement ps, Object[] params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(i + 1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
