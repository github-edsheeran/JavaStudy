package JueJin.Thread.part6;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试守护线程
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:58
 **/
public class MyThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
