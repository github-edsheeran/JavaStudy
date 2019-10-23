package SXT._12ManualSORMFramework.test;

import SXT._12ManualSORMFramework.core.Query;
import SXT._12ManualSORMFramework.core.QueryFactory;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试类
 * @author: LiuDongMan
 * @createdDate: 2019-10-23 14:52
 **/
public class Test {
    public static void test01() {
        Query query = QueryFactory.createQuery();
        String sql = "SELECT empName FROM emp WHERE id = ?";

        query.queryValue(sql, new Object[]{4});
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 3000; i++) {
            test01();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
