package SXT._5Thread.part17;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解InheritableThreadLocal，继承上下文环境的数据，拷贝一份给子线程（并不是共享数据）
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 09:11
 **/
public class InheritableThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(2);
        System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

        // 线程由main主线程开辟，因此，有着主线程的ThreadLocal中的数据拷贝副本
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());

            // 只是数据拷贝，并不是数据共享，因此修改不会影响main主线程在ThreadLocal中的值
            threadLocal.set(100);
            System.out.println(Thread.currentThread().getName() + " --> " + threadLocal.get());
        }).start();
    }
}
