package SXT._11JDBC.part1;

import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试事务
 * 事务开始于：
 * 1.连接到数据库上，并执行一条DML语句(INSERT、UPDATE、DELETE)；
 * 2.前一个事务结束后，又输入了另外一条DML语句；
 * 事务结束于：
 * 1.执行COMMIT或ROLLBACK语句；
 * 2.执行一条DDL语句，例如CREATE TABLE语句，在这种情况下，会自动执行COMMIT语句；
 * 3.执行一条DCL语句，例如GRANT语句，在这种情况下，会自动执行COMMIT语句；
 * 4.断开与数据库的连接；
 * 5.执行了一条DML语句，该语句却失败了，在这种情况下，会为这个无效的DML语句执行ROLLBACK语句；
 * @author: LiuDongMan
 * @createdDate: 2019-09-28 10:55
 **/
public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps01 = null;
        PreparedStatement ps02 = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");

            conn.setAutoCommit(false);  // 默认为自动提交事务，现在设置为手动提交

            ps01 = conn.prepareStatement("INSERT INTO T_USER (USER_NAME, PASSWORD, REGISTER_TIME) VALUES (?, ?, ?)");
            ps02 = conn.prepareStatement("INSERT INTO T_USER (USER_NAME, PASSWORD, REGISTER_TIME) VALUES (?, ?, ?)");   // 测试可以把一个字段删除掉

            ps01.setObject(1, "张三");
            ps01.setObject(2, "123456");
            ps01.setObject(3, new java.sql.Date(System.currentTimeMillis()));

            ps01.execute();

            ps02.setObject(1, "李四");
            ps02.setObject(2, "123456");
            ps02.setObject(3, new java.sql.Date(System.currentTimeMillis()));

            ps02.execute();

            conn.commit();  // 手动提交事务
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            try {
                conn.rollback();    // 如果出现异常，则进行回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
