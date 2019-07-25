package JueJin.Thread.part2;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试线程不共享数据的情况
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 11:15
 **/
public class MyThread extends Thread {
    private int count = 5;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由" + Thread.currentThread().getName() + "计算, count = " + count);
        }
    }
}
