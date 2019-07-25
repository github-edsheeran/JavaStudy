package JueJin.Thread.part3;

/**
 * @program: Homework
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 11:20
 **/
public class Test {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread a = new Thread(myThread, "A");
        Thread b = new Thread(myThread, "B");
        Thread c = new Thread(myThread, "C");
        Thread d = new Thread(myThread, "D");
        Thread e = new Thread(myThread, "E");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
