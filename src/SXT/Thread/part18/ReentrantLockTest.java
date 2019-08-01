package SXT.Thread.part18;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单认识ReentrantLock
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 15:08
 **/
public class ReentrantLockTest {
    private ReentrantLock lock = new ReentrantLock();

    public void a() {
        lock.lock();
        System.out.println(lock.getHoldCount());
        b();
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

    public void b() {
        lock.lock();
        System.out.println(lock.getHoldCount());
        // 后期的一些业务逻辑代码
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        test.a();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.lock.getHoldCount());
    }
}
