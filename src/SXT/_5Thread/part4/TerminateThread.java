package SXT._5Thread.part4;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解如何终止线程
 * 线程中的stop，destroy方法不推荐使用，可以通过对外提供标识来控制线程的生命周期
 * @author: LiuDongMan
 * @createdDate: 2019-07-21 21:36
 */
public class TerminateThread implements Runnable {
    /**
     * 对外提供的标识
     */
    private boolean flag = true;
    private String name;

    public TerminateThread() {

    }

    public TerminateThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i = 1;

        while (flag) {
            System.out.println(name + "-" + i++);
        }
    }

    public void terminateThread() {
        this.flag = false;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        TerminateThread t1 = new TerminateThread("线程1");
        new Thread(t1).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程: " + i);

            if (i == 88) {
                t1.terminateThread();
                System.out.println(t1.getName() + "终止了!");
            }
        }
    }
}
