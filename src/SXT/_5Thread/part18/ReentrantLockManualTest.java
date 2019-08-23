package SXT._5Thread.part18;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 手动实现可重入锁
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 10:51
 **/
public class ReentrantLockManualTest {
    private ReentrantLockManual lock = new ReentrantLockManual();

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
        ReentrantLockManualTest test = new ReentrantLockManualTest();
        test.a();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.lock.getHoldCount());
    }
}

class ReentrantLockManual {
    private boolean lockFlag = false;   // 是否被占用，默认为false，这样第一个线程进来的时候能够获得锁之后直接运行
    private int holdCount = 0;  // 计数器，每次使用锁，计数器值加1，释放锁，计数器减1，当值为0时，线程完全释放持有的锁
    private Thread lockBy = null;   // 存储获得锁的线程

    /**
     * 使用锁
     */
    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();  // 获取当前线程

        // 如果锁被占用且当前线程不是获得锁的那个线程则当前线程进入等待阻塞状态，否则不用等待，直接可以继续运行
        while (lockFlag && lockBy != currentThread) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lockFlag = true;
        lockBy = currentThread;
        holdCount++;
    }

    /**
     * 释放锁
     */
    public synchronized void unlock() {
        if (Thread.currentThread() == lockBy) {
            holdCount--;    // 每次释放锁，值减1

            // 如果线程持有锁的计数器值为0，则完全释放锁，唤醒被阻塞的线程，且存储获得锁的线程变量赋值为null
            if (holdCount == 0) {
                lockFlag = false;
                this.notifyAll();
                lockBy = null;
            }
        }
    }

    public int getHoldCount() {
        return holdCount;
    }
}
