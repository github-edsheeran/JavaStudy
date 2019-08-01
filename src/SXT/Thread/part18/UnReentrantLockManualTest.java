package SXT.Thread.part18;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 手动实现不可重入锁
 * @author: LiuDongMan
 * @createdDate: 2019-08-01 10:05
 **/
public class UnReentrantLockManualTest {
    private UnReentrantLockManual lock = new UnReentrantLockManual();

    public void a() {
        lock.lock();
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        // 后期的一些业务逻辑代码
        lock.unlock();
    }

    public static void main(String[] args) {
        UnReentrantLockManualTest test = new UnReentrantLockManualTest();
        test.a();
    }
}

class UnReentrantLockManual {
    // 是否被占用
    private boolean lockFlag = false;

    public synchronized void lock() {
        // 如果锁被持有，其他线程进入等待阻塞状态
        while (lockFlag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.lockFlag = true;
    }

    public synchronized void unlock() {
        this.lockFlag = false;
        this.notifyAll();
    }
}

