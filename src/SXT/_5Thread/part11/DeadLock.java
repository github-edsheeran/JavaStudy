package SXT._5Thread.part11;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解死锁问题
 * @author: LiuDongMan
 * @createdDate: 2019-07-29 20:47
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup girl01 = new Makeup("王菲", 1);
        Makeup girl02 = new Makeup("张柏芝", 2);

        new Thread(girl01).start();
        new Thread(girl02).start();
    }
}

class Lipstick {

}

class Mirror {

}

class Makeup implements Runnable {
    // 声明成静态表示有且只有一只口红和一面镜子，即便是有多个对象也是如此
    private static Lipstick lipstick = new Lipstick();
    private static Mirror mirror = new Mirror();

    private String girlName;
    private int choice;

    public Makeup(String girlName, int choice) {
        this.girlName = girlName;
        this.choice = choice;
    }

    public Makeup() {
    }

    @Override
    public void run() {
        makeup();
    }

    private void makeup() {
        if (choice == 1) {
            synchronized (lipstick) {
                System.out.println(this.girlName + "涂口红...");

                // 一秒后想照镜子
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * 同一个块中持有了两个对象的锁，造成死锁：
                 * 假设A、B两个线程同时访问，A线程choice值为1，B线程值为0，此时，A线程持有了口红的锁，而B线程持有了镜子的锁，在A线程等
                 * 待一秒之后需要持有镜子的锁才能进行下去，而B线程在两秒之后需要持有口红的锁才能进行下去，但是彼此之间又没有释放，因此造
                 * 成了死锁
                 * 解决办法是将这段代码移出去即可
                 */
//                synchronized (mirror) {
//                    System.out.println(this.girlName + "照镜子...");
//                }
            }

            synchronized (mirror) {
                System.out.println(this.girlName + "照镜子...");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + "照镜子...");

                // 两秒后想涂口红
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                synchronized (lipstick) {
//                    System.out.println(this.girlName + "涂口红...");
//                }
            }

            synchronized (lipstick) {
                System.out.println(this.girlName + "涂口红...");
            }
        }
    }
}
