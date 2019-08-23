package CoreJavaVolumePartOne.chapter14.test.part2;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 测试线程中的join方法
 * @author: LiuDongMan
 * @createdDate: 2019-07-02 20:48
 **/
public class Main {
    public static void main(String[] args) {
//        // 不加join方法会发现主线程比子线程早结束
//        System.out.println(_5Thread.currentThread().getName() + " 主线程运行开始!");
//        Thread1 mTh1 = new Thread1("A");
//        Thread1 mTh2 = new Thread1("B");
//        mTh1.start();
//        mTh2.start();
//        System.out.println(_5Thread.currentThread().getName() + " 主线程运行结束!");

        // 加上join方法，这个时候，主线程一定会等子线程都结束了才结束
        System.out.println(Thread.currentThread().getName() + " 主线程运行开始!");
        Thread1 mTh1 = new Thread1("A");
        Thread1 mTh2 = new Thread1("B");
        mTh1.start();
        mTh2.start();

        try {
            mTh1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            mTh2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 主线程运行结束!");
    }
}

class Thread1 extends Thread {
    private String name;

    public Thread1(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程运行开始!");

        for (int i = 0; i < 5; i++) {
            System.out.println("子线程: " + name + "运行: " + i);

            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
    }
}
