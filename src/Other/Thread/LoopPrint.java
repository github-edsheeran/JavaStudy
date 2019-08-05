package Other.Thread;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 三线程打印ABC，A线程打印10次A，B线程打印10次B，C线程打印10次C，要求线程同时运行，交替打印10次ABC
 * @author: LiuDongMan
 * @createdDate: 2019-08-04 21:25
 */
public class LoopPrint implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public LoopPrint(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;

        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);

                    count--;

                    self.notify();
                }

                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        LoopPrint lp01 = new LoopPrint("A", c, a);
        LoopPrint lp02 = new LoopPrint("B", a, b);
        LoopPrint lp03 = new LoopPrint("C", b, c);

        new Thread(lp01).start();

        Thread.sleep(100);

        new Thread(lp02).start();

        Thread.sleep(100);

        new Thread(lp03).start();

        Thread.sleep(100);
        /**
         * 三个对象a，b，c；三个线程t1、t2、t3；
         * t1线程开始运行，调用run方法，此时，t1线程获得c和a对象的锁，并打印"A"，同时，唤醒在a对象上等待的其中一个线程，以及，获得了c对
         * 象锁的当前线程主动释放锁进入等待，synchronized语句块结束后，a对象锁释放掉；等待100毫秒后，t2线程开始运行，调用run方法，这个
         * 时候只有获得了a对象的锁才能继续运行下去，但是在之前的t1线程中，run方法运行完毕后已经释放了a对象的锁，因此t2线程中的run方法可
         * 以继续运行，此时打印"B"，同时唤醒在b对象上等待的其中一个线程，以及，获得了a对象锁的当前线程主动释放锁进入等待，synchronized
         * 语句块结束后，b对象锁被释放掉；等待100毫秒后，t3线程开始运行，调用run方法，这个时候只有获得了b对象的锁才能继续运行下去，但是
         * 在之前的t2线程中，synchronized语句块结束后，已经释放了b对象的锁，因此t3线程中的run方法可以继续运行，此时打印"C"，同时唤醒在
         * c对象上等待的其中一个线程，以及，获得了b对象锁的当前线程进入等待，synchronized语句块结束后，c对象锁被释放掉；之后便以此类推。
         *
         * 以现在电脑的运行速度，为什么不会出现打印AAABBBCCC的情况的原因：
         * 以t1线程为例，在打印了A之后，释放掉了c对象的锁，之后再进行循环的时候，由于没有得到c对象的锁，进入阻塞等待的状态，直到t3线程运
         * 行，结束一次循环后，唤醒在c对象等待的其中一个线程。
         *
         */
    }
}
