package SXT._12ManualSORMFramework.pool;

import SXT._12ManualSORMFramework.core.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 连接池类
 * @author: LiuDongMan
 * @createdDate: 2019-10-23 08:57
 **/
public class DBConnectionPool {
    private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();
    private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();
    private List<Connection> pool;  // 由于连接池对象在后面的使用中被设置为静态属性，因此，这个地方不用设置成静态，也能保证共用的是一个list

    public DBConnectionPool() {
        initConnectionPool();
    }

    /**
     * 初始化连接池
     */
    public void initConnectionPool() {
        if (pool == null) {
            pool = new ArrayList<>();
        }

        while (pool.size() < POOL_MIN_SIZE) {
            pool.add(DBManager.createConnection());
        }
    }

    /**
     * 从连接池中取出一个连接
     * @return
     */
    public synchronized Connection getConnection() {
        int lastIndex = pool.size() - 1;    // 最后一个连接的索引，连接池取连接往往是从最后一个开始取
        Connection conn = pool.get(lastIndex);  // 记住一定要提前取出来，否则后面的移除操作会导致出现索引越界的异常
        pool.remove(lastIndex); // 取完之后从连接池中移除
        return conn;
    }

    /**
     * 将连接放回连接池
     * @param conn
     */
    public synchronized void close(Connection conn) {
        if (pool.size() >= POOL_MAX_SIZE) { // 如果连接池的数量超过其最大数量，则真正的关闭
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            pool.add(conn); // 否则，将连接再次放回连接池中，便于重用
        }
    }
}
