package CoreJavaVolumePartOne.chapter14.test.part3;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 测试线程中的yield方法
 * @author: LiuDongMan
 * @createdDate: 2019-07-02 21:00
 **/
public class Main {
    public static void main(String[] args) {
//        ThreadYield yt1 = new ThreadYield("张三");
//        ThreadYield yt2 = new ThreadYield("李四");
//        yt1.start();
//        yt2.start();

        /**
         * 第一种情况：李四（线程）当执行到30时会CPU时间让掉，这时张三（线程）抢到CPU时间并执行。
         * 第二种情况：李四（线程）当执行到30时会CPU时间让掉，这时李四（线程）抢到CPU时间并执行。
         */

        ThreadPriority t1 = new ThreadPriority("王五");
        ThreadPriority t2 = new ThreadPriority("赵六");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}

class ThreadYield extends Thread {
    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);

            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                this.yield();
            }
        }
    }
}

class ThreadPriority extends Thread {
    private String name;

    public ThreadPriority(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + i);

            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
