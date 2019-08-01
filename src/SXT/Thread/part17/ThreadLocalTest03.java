package SXT.Thread.part17;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 认识ThreadLocal上下文环境所带来的影响
 * 1.构造器：哪里调用，就属于哪里，看对象初始化是在哪个线程中进行的；
 * 2.run方法：本线程自身的
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 08:54
 **/
public class ThreadLocalTest03 {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        /**
         * 由于new MyThread()对象初始化的操作是在main主线程中进行的，因此，通过构造器获取线程名的时候得到的是主线程信息，因此，构造器中，
         * 对ThreadLocal值的修改只会影响main主线程；而当线程启动，调用run方法的时候，这个时候获取的则是新开线程的信息
         */
        new Thread(new MyThread01()).start();
        new Thread(new MyThread01()).start();
    }

    static class MyThread01 implements Runnable {
        public MyThread01() {
            threadLocal.set(-100);
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
            threadLocal.set(99);
            new Thread(new MyThread02()).start();
        }
    }

    static class MyThread02 implements Runnable {
        public MyThread02() {
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }

        @Override
        public void run() {

        }
    }


}
