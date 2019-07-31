package SXT.Thread.part15;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解volatile，用于保证线程间数据的及时可见性
 * @author: LiuDongMan
 * @createdDate: 2019-07-31 09:19
 **/
public class VolatileTest {
    private volatile static int num = 0;    // 加和不加volatile自己测试下运行结果

    public static void main(String[] args) {
        new Thread(() -> {
            // 通过编写死循环让CPU一直处理这个线程，不能及时处理变量值的改变
            while (num == 0) {

            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;    // 如果不加volatile进行修饰，CPU会一直被上面线程的死循环吸引，不能及时处理num值的变更
    }
}
