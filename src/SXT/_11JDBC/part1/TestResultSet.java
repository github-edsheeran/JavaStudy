package SXT._11JDBC.part1;

import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试JDBC查询返回的结果集ResultSet
 * 需要注意的是，不论是Connection、PreparedStatement还是ResultSet都需要关闭，且遵循先打开的后关闭原则
 * @author: LiuDongMan
 * @createdDate: 2019-09-28 09:31
 **/
public class TestResultSet {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");
            String sql = "SELECT ID, USER_NAME, PASSWORD, REGISTER_TIME FROM T_USER";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                // 这个地方也可以使用getObject的方式获取返回的参数
                System.out.println(rs.getInt(1) + " --> " + rs.getString(2) + " --> " + rs.getString(3) + " --> " + rs.getDate(4));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 注意：三个关闭都需要放在单独的try-catch块中，如果放在一起，一个发生异常，会导致其他几个不会正常关闭
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
}
