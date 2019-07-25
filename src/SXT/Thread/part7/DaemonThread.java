package SXT.Thread.part7;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解守护线程
 * @author: LiuDongMan
 * @createdDate: 2019-07-23 21:23
 */
public class DaemonThread {
    public static void main(String[] args) {
        You you = new You();
        God god = new God();

        Thread t1 = new Thread(you);
        Thread t2 = new Thread(god);

        t2.setDaemon(true); // 将用户线程设置为守护线程

        t1.start();
        t2.start();
    }
}

class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100 * 365; i++) {
            System.out.println("You live well...");
        }
    }
}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you...");
        }
    }
}
