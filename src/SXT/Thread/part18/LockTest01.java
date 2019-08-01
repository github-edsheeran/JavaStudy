package SXT.Thread.part18;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 锁是可重入的，即可以延续使用。简单理解为，进入到房屋之后，既可以进入厨房、也可以进入厕所，甚至可以进入卧室
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 10:00
 **/
public class LockTest01 {
    public void test() {
        // 第一次获得锁
        synchronized (this) {
            while (true) {
                // 第二次获得锁，如果锁是不可重入的话，在这个地方可能会等待，即发生阻塞
                synchronized (this) {
                    System.out.println("ReentrantLock!");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new LockTest01().test();
    }
}
