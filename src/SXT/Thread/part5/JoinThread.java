package SXT.Thread.part5;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解线程的join方法
 * @author: LiuDongMan
 * @createdDate: 2019-07-22 21:24
 */
public class JoinThread {
    public static void main(String[] args) {
        System.out.println("这是一个父亲让儿子买烟的故事...");
        Father father = new Father();
        Thread t1 = new Thread(father);
        t1.start();
    }
}

class Father implements Runnable {

    @Override
    public void run() {
        System.out.println("父亲烟瘾犯了，掏出钱让儿子买烟...");

        Son son = new Son();
        Thread sonThread = new Thread(son);
        sonThread.start();

        try {
            sonThread.join();   // 哪个线程对象调用join方法，哪个线程就插入到队列中，而方法体所属的线程对象则进入到阻塞状态，直到插队线程运行结束才会继续运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父亲接过儿子买来的烟，把零钱给了儿子...");
    }
}

class Son implements Runnable {

    @Override
    public void run() {
        System.out.println("儿子接过钱，出门买烟，路过游戏厅，进入玩了十秒...");

        for (int i = 0; i < 10; i++) {
            System.out.println(i + "秒过去了...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("突然记起来父亲的烟还没买，于是出去买完烟回家...");
    }
}