package JueJin.Thread.part3;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试线程共享数据的情况
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 11:19
 **/
public class MyThread extends Thread {
    private int count = 5;

    @Override
    public synchronized void run() {
        super.run();
        count--;
        System.out.println("由" + Thread.currentThread().getName() + "计算, count = " + count);
    }
}
