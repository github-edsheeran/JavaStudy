package SXT._11JDBC.part1;

import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试PreparedStatement
 * @author: LiuDongMan
 * @createdDate: 2019-09-28 09:13
 **/
public class TestPreparedStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");
            String sql = "INSERT INTO T_USER (USER_NAME, PASSWORD, REGISTER_TIME) VALUES (?, ?, ?)";    // 通过占位符的方式避免SQL注入以及方便传参
            PreparedStatement ps = conn.prepareStatement(sql);

            /**
             * 两种方式设置占位符参数:
             * 1.明确指定参数类型；
             * 2.通过Object的方式统一赋值；
             */
//            ps.setString(1, "张三"); // 需要注意的是，JDBC中的占位符索引是从1开始的
//            ps.setString(2, "123456");
//            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));    // 注意这个地方的Date是java.sql.Date

            ps.setObject(1, "李四");
            ps.setObject(2, "123456");
            ps.setObject(3, new java.sql.Date(System.currentTimeMillis()));

            ps.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
