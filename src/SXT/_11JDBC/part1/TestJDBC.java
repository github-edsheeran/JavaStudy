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
            // 这是加载低版本驱动类的方式，8.0之后的版本需要用新的方式进行加载
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            Class.forName("com.mysql.cj.jdbc.Driver");
            long startTime = System.currentTimeMillis();

            /**
             * 8.0 以上版本不需要建立SSL连接的，需要显示关闭
             * 连接对象内部其实包含了Socket对象，是一个远程连接，比较耗时，后期真正的开发中，为了提高效率，都会使用连接池来管理连接对象
             */
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");

            long endTime = System.currentTimeMillis();

            System.out.println(conn);
            System.out.println("建立连接耗时：" + (endTime - startTime) + "毫秒");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
