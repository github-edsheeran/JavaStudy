package SXT._11JDBC.part1;

import java.io.*;
import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试CLOB(Character Large Object)文本大对象的使用
 * @author: LiuDongMan
 * @createdDate: 2019-09-30 17:06
 **/
public class TestCLOB {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reader reader = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8", "root", "123456");
            ps = conn.prepareStatement("INSERT INTO T_USER (USER_NAME, TEST_CLOB) VALUES (?, ?)");

            // 将文件内容传入到数据库中
//            ps.setString(1, "王五");
//            ps.setClob(2, new FileReader("Files/2.txt"));

            // 将字符串传入到数据库中
//            ps.setString(1, "赵六");
//            ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("Hello, world!".getBytes()))));
//
//            ps.executeUpdate();

            // 将数据库中的CLOB数据取出来
            ps = conn.prepareStatement("SELECT * FROM T_USER WHERE ID = ?");

            ps.setObject(1, 200012);

            rs = ps.executeQuery();

            while (rs.next()) {
                Clob clob = rs.getClob("TEST_CLOB");    // 根据字段获取相应的对象
                reader = clob.getCharacterStream();  // 根据对象获取文本输入流
                int flush01 = -1;

                while ((flush01 = reader.read()) != -1) {
                    System.out.print((char) flush01);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

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
