package SXT._11JDBC.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单测试Statement，日常工作中，使用的较少，原因在于后期参数需要手动拼，且容易发生SQL注入的问题
 * @author: LiuDongMan
 * @createdDate: 2019-09-28 08:58
 **/
public class TestStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8","root","123456");
            Statement statement = conn.createStatement();
            // 后期类似于"张三"这些值是作为参数加入到SQL语句中，拼写相对来说比较麻烦
            String sql = "INSERT INTO T_USER (USER_NAME, PASSWORD, REGISTER_TIME) VALUES ('张三', '123456', now())";
//            boolean flag = statement.execute(sql);

            // 使用Statement容易发生SQL注入的问题
            String id = "1 or 1 =1";
            sql = "DELETE FROM T_USER WHERE ID = " + id;
            boolean flag = statement.execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
