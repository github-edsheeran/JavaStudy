package JueJin.Thread.part5;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试线程优先级的继承性
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:45
 **/
public class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread1 run priority = " + this.getPriority());
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
