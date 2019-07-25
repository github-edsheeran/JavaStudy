package JueJin.Thread.part5;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试线程优先级的继承性
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:46
 **/
public class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread2 run priority = " + this.getPriority());
    }
}
