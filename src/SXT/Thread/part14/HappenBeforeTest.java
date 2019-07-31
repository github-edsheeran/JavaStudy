package SXT.Thread.part14;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解多线程情况下指令重排所带来的影响
 * @author: LiuDongMan
 * @createdDate: 2019-07-30 21:22
 */
public class HappenBeforeTest {
    private static int a = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            a = 0;
            flag = false;

            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }

                if (a == 0) {
                    //这个得多运行自己才会出来
                    System.out.println("Happen before，此时a的值为: " + a + "，但是只有a == 0为true的情况下，才能打印这句话。");
                }
            });

            t1.start();
            t2.start();

            System.out.println(Thread.activeCount());

            // 调用线程的join方法，合并线程，这样，主线程只有等待t1、t2线程运行完毕返回结果之后才会继续运行
            t1.join();
            t2.join();
        }
    }
}
