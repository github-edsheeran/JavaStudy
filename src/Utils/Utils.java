package Utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 工具类
 * @author: LiuDongMan
 * @createdDate: 2019-09-03 08:21
 **/
public class Utils {
    /**
     * 批量调用对象的close方法，必须实现Closeable接口
     * @param beans
     */
    public static void release(Closeable... beans) {
        for (Closeable bean : beans) {
            try {
                bean.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
