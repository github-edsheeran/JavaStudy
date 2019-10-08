package SXT._11JDBC.part1;

import java.io.*;
import java.sql.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试二进制对象的使用
 * @author: LiuDongMan
 * @createdDate: 2019-10-08 08:44
 **/
public class TestBLOB {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8", "root", "123456");
            ps = conn.prepareStatement("INSERT INTO T_USER (USER_NAME, TEST_BLOB) VALUES (?, ?)");

            // 将图片的二进制内容输入到数据库中
//            ps.setString(1, "田七");
//            ps.setBlob(2, new FileInputStream("Images/emoji.jpg"));
//
//            ps.executeUpdate();

            // 将数据库中的CLOB对象取出来存到电脑上
            ps = conn.prepareStatement("SELECT * FROM T_USER WHERE ID = ?");

            ps.setObject(1, 200013);

            rs = ps.executeQuery();
            os = new FileOutputStream("D:/1.jpg");

            while (rs.next()) {
                Blob blob = rs.getBlob("TEST_BLOB");
                is = blob.getBinaryStream();
                int flush01 = -1;

                while ((flush01 = is.read()) != -1) {
                    os.write(flush01);
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
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (os != null) {
                    os.close();
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
