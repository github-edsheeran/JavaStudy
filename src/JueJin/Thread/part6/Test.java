package JueJin.Thread.part6;

/**
 * @program: Homework
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 15:00
 **/
public class Test {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.setDaemon(true); // 将该线程设置为守护线程，一旦所有用户线程都结束运行，守护线程会随JVM一起结束工作，也就是该例子中的线程中的无限循环会有结束的一刻
            thread.start();
            Thread.sleep(5000);
            System.out.println("我离开thread对象也不再打印了,也就是停止了!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
