package JueJin.Thread.part5;

/**
 * @program: Homework
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:47
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println("Main Thread begin priority = " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("Main Thread end priority = " + Thread.currentThread().getPriority());
        MyThread1 thread1 = new MyThread1();
        thread1.start();
    }
}
