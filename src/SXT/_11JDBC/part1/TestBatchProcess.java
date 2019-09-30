package SXT._11JDBC.part1;

import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试预处理
 * 需要注意的是，批处理最好还是使用Statement，因为PreparedStatement有预处理这道手续，如果批处理数据量太大的话，可能会报异常
 * @author: LiuDongMan
 * @createdDate: 2019-09-28 10:06
 **/
public class TestBatchProcess {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");

            conn.setAutoCommit(false);  // 将事务设置为手动提交

            statement = conn.createStatement();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 20000; i++) {
                statement.execute("INSERT INTO T_USER (USER_NAME, PASSWORD, REGISTER_TIME) VALUES ('张三" + i + "', '123456', NOW())");
            }

            statement.executeBatch();
            conn.commit();  // 手动提交事务

            long endTime = System.currentTimeMillis();

            System.out.println("插入20000条数据耗时：" + (endTime - startTime) + "毫秒");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
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
}
