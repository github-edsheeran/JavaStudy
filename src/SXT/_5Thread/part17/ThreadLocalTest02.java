package SXT._5Thread.part17;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: ThreadLocal中，每个线程都要自己的数据存储空间，互不影响
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 08:47
 **/
public class ThreadLocalTest02 {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            // 开启多个线程，每个线程的数据都是独立的
            new Thread(new MyThread()).start();
        }
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            int rest = threadLocal.get();

            System.out.println(Thread.currentThread().getName() + "得到了: " + threadLocal.get());

            // 减去得到的数据之后再进行set操作
            threadLocal.set(rest - 1);

            // 需要再重新调用get方法获取最新的值
            System.out.println(Thread.currentThread().getName() + "还剩下: " + threadLocal.get());
        }
    }

}
