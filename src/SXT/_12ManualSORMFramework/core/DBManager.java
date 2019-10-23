package SXT._12ManualSORMFramework.core;

import SXT._12ManualSORMFramework.bean.Configuration;
import SXT._12ManualSORMFramework.pool.DBConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 根据配置信息，进行连接对象的管理(增加连接池的功能)
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 15:56
 **/
public class DBManager {
    /**
     * 配置文件对象
     */
    private static Configuration conf;
    /**
     * 连接池对象
     */
    private static DBConnectionPool pool;

    static {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("SXT/_12ManualSORMFramework/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration(properties.getProperty("driver"), properties.getProperty("url"), properties.getProperty("user"),
                properties.getProperty("password"), properties.getProperty("usingDB"), properties.getProperty("srcPath"), properties.getProperty("poPackage"),
                properties.getProperty("queryClass"), Integer.valueOf(properties.getProperty("poolMaxSize")), Integer.valueOf(properties.getProperty("poolMinSize")));

        // 加载TableContext，读取相关信息
        System.out.println(TableContext.class);
    }

    /**
     * 封装获取MySQL连接的代码，后期可以加入Oracle和SQLServer的
     * @return
     */
    public static Connection getConnection() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }

        return pool.getConnection();    // 从连接池获取连接

//        try {
//            Class.forName(conf.getDriver());
//            return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());  // 直接建立连接，后期增加连接池处理，提高效率
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    /**
     * 创建连接对象
     * @return
     */
    public static Connection createConnection() {
        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());  // 直接建立连接，后期增加连接池处理，提高效率
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

        pool.close(conn);
    }

    public static Configuration getConf() {
        return conf;
    }
}
