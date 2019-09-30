package SXT._11JDBC.part1;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试Date、Time、Timestamp之间的区别
 * 1.Date只包含年月日；
 * 2.Time只包含时分秒；
 * 3.Timestamp包含年月日时分秒；
 * 需要注意的是，连接数据库的url中，时区需要指定为GMT%2B8，即北京时区，也就是东八区，领先UTC(全球标准时间)八个小时
 * @author: LiuDongMan
 * @createdDate: 2019-09-29 08:24
 **/
public class TestDateDifference {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 时区需要额外注意
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");
//            ps = conn.prepareStatement("INSERT INTO T_USER (USER_NAME, PASSWORD, TEST_DATE, TEST_TIME, TEST_TIMESTAMP) VALUES (?, ?, ?, ?, ?)");
//
//            ps.setString(1, "张三");
//            ps.setString(2, "123456");
//            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
//            ps.setTime(4, new Time(System.currentTimeMillis()));
//            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//
//            int result = ps.executeUpdate();
//
//            System.out.println(result);

            ps = conn.prepareStatement("SELECT * FROM T_USER WHERE TEST_DATE >= ? AND TEST_DATE <= ?");

            ps.setDate(1, new java.sql.Date(strToDate("2019-09-27")));
            ps.setDate(2, new java.sql.Date(strToDate("2019-09-28")));

            System.out.println(ps.toString());  // 打印对象信息以及最终的SQL语句

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("USER_NAME") + " --> " + rs.getDate("TEST_DATE"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            try {
                conn.rollback();    // 如果出现异常，则进行回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public static long strToDate(String str) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return df.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
