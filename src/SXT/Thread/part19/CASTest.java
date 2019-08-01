package SXT.Thread.part19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解CAS（Compare And Set）比较并交换
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 15:56
 **/
public class CASTest {
    // 库存
    private static AtomicInteger stock = new AtomicInteger(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // 模拟网络延时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int rest = stock.decrementAndGet();

                // 不能在这个地方进行打印操作，因为decrementAndGet方法返回的是减掉之后的值
                //System.out.println(Thread.currentThread().getName() + "抢到了1件商品，还剩下: " + rest + "件商品");

                if (rest < 1) {
                    System.out.println("抢完了！");
                    return;
                }

                System.out.println(Thread.currentThread().getName() + "抢到了1件商品，还剩下: " + rest + "件商品");
            }).start();
        }
    }
}
