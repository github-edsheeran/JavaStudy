package SXT._11JDBC.part1;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 将JDBC相关的代码进行封装，提高可重用性
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 09:24
 **/
public class JDBCUtils {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("SXT/_11JDBC/part1/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装获取MySQL连接的代码，后期可以加入Oracle和SQLServer的
     * @return
     */
    public static Connection getMySQLConnection() {
        try {
            Class.forName(properties.getProperty("mysqlDriver"));
            return DriverManager.getConnection(properties.getProperty("mysqlURL"), properties.getProperty("mysqlUser"),
                    properties.getProperty("mysqlPassword"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 封装关闭的代码
     * @param rs
     * @param ps
     * @param conn
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
