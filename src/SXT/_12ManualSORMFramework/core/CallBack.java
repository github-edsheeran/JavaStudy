package SXT._12ManualSORMFramework.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 回调接口
 * @author: LiuDongMan
 * @createdDate: 2019-10-21 16:47
 **/
public interface CallBack {
    Object doQuery(Connection conn, PreparedStatement ps, ResultSet rs);
}
