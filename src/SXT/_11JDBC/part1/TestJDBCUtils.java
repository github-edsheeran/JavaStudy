package SXT._11JDBC.part1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试JDBCUtils
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 10:19
 **/
public class TestJDBCUtils {
    public static void main(String[] args) {
        Connection conn = JDBCUtils.getMySQLConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM T_USER WHERE ID = ?");

            ps.setObject(1, 20008);

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("USER_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
    }
}
