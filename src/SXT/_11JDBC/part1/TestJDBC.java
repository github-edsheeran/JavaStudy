package SXT._11JDBC.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试JDBC连接
 * @author: LiuDongMan
 * @createdDate: 2019-09-27 14:36
 **/
public class TestJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "987654321");

            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
