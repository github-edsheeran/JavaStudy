package SXT._5Thread.part17;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解ThreadLocal，可以理解为是多个线程的共享区域，但是其中的每一个线程又有着自己的独立空间，数据互不影响
 * @author: LiuDongMan
 * @createdDate: 2019-07-31 21:52
 */
public class ThreadLocalTest01 {
    /**
     * 官方推荐使用private static进行修饰
     */
//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 初始化ThreadLocal的两种方式：
     * 1.匿名内部类
     * 2.Lambda表达式
     */
//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 200;
//        }
//    };

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        // 获取值
        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

        // 两个线程在ThreadLocal中有着属于自己独立的空间，数据互不影响
        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();

        threadLocal.set(99);

        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            // 设置值
            threadLocal.set((int) (Math.random() * 99));
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }
    }
}
