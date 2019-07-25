package JueJin.Thread.part4;

/**
 * @program: Homework
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:32
 **/
public class Test {
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Main catch");
            e.printStackTrace();
        }
    }
}
