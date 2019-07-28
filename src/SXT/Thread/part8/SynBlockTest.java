package SXT.Thread.part8;

import java.util.ArrayList;
import java.util.List;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解同步块的作用
 * @author: LiuDongMan
 * @createdDate: 2019-07-27 16:02
 */
public class SynBlockTest {
    public static void main(String[] args) {
//        Account account = new Account(1000, "结婚礼金");
//        SynDraw husband = new SynDraw(account, 80);
//        SynDraw wife = new SynDraw(account, 90);
//        new Thread(husband, "悲催的丈夫").start();
//        new Thread(wife, "开心的妻子").start();

//        List<String> list = new ArrayList<>();
//
//        for (int i = 0; i < 10000; i++) {
//            new Thread(() -> {
//                synchronized (list) {
//                    list.add(Thread.currentThread().getName());
//                }
//            }).start();
//        }
//
//        // 防止出现前面线程未跑完而主线程跑完的情况，加入sleep
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(list.size());

        SynWeb12306 ex01 = new SynWeb12306();
        new Thread(ex01, "码畜").start();
        new Thread(ex01, "码农").start();
        new Thread(ex01, "码蝗").start();
    }
}

class SynDraw implements Runnable {
    private Account account;
    private int drawMoney;  // 取的钱数
    private int pocketMoney;    // 口袋里面的总数

    public SynDraw(Account account, int drawMoney) {
        this.account = account;
        this.drawMoney = drawMoney;
    }

    public SynDraw() {

    }

    @Override
    public void run() {
        test();
    }

    /**
     * 通过运行可以发现，加入synchronized关键字之后依然出现线程不安全的问题，原因在于目标不对，因此造成锁定失败。这个地方，锁定的不应该是
     * this，而应该是account对象才行。
     */
    public void test() {
        // 通过提前判断账户余额，达到提高效率的作用
        if (account.getMoney() <= 0) {
            return;
        }

        synchronized (account) {
            if (account.getMoney() - drawMoney < 0) {
                return;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.setMoney(account.getMoney() - drawMoney);
            pocketMoney += drawMoney;
            System.out.println(Thread.currentThread().getName() + " --> 账户余额为: " + account.getMoney());
            System.out.println(Thread.currentThread().getName() + " --> 口袋里面的总数为: " + pocketMoney);
        }
    }
}

class SynWeb12306 implements Runnable {
    private int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //test01();
            //test02();
            //test03();
            test04();
        }
    }

    public synchronized void test01() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }

    /**
     * 同步块范围大会影响效率
     */
    public void test02() {
        // 由于考虑到要同时锁ticketNums和flag的情况，因此直接从成员变量上升到锁当前对象this
        synchronized (this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    /**
     * 出现线程不安全情况，范围太小，锁定失败
     * 原因：同步块在锁定资源的时候，其中的资源是不能变的，例如，this，虽然对象的属性在发生变化，但大的对象未发生变化；而ticketNums作为
     * 一个int变量，在下面的代码中一直发生着变化，因此锁不住
     */
    public void test03() {
        synchronized (Integer.valueOf(ticketNums)) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    /**
     * 依然出现了线程不安全的情况
     * 原因：假设有a、b、c三个线程，且ticketNums值为1，这个时候，a线程进入test04方法，进行判断之后，进入等待环节，而在这个过程中，b、c
     * 线程以同样的方式进入方法，这个时候，三个线程抢同一张票，出现线程不安全的情况
     */
    public void test04() {
        synchronized (Integer.valueOf(ticketNums)) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }

    /**
     * 保证线程安全的同时，尽可能的提高效率，要锁定合理的范围(不是指代码，而是指数据的完整性)
     */
    public void test05() {
        /**
         * 考虑的是没有票的情况。需要注意的是，如果只有这儿的判断，没有下面的考虑最后一张票的判断，依然会出现线程不安全的情况
         * 涉及到双重检测
         */
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        synchronized (this) {
            if (ticketNums <= 0) {  // 考虑的是只剩最后一张票的情况
                flag = false;
                return;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }
}


