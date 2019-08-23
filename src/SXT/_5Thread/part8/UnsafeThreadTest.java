package SXT._5Thread.part8;

import java.util.ArrayList;
import java.util.List;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解线程同步
 * @author: LiuDongMan
 * @createdDate: 2019-07-23 21:44
 */
public class UnsafeThreadTest {
    public static void main(String[] args) {
//        UnsafeWeb12306 web = new UnsafeWeb12306();
//        new _5Thread(web, "张三").start();
//        new _5Thread(web, "李四").start();
//        new _5Thread(web, "王五").start();

//        Account account = new Account(1000000, "结婚礼金");
//        Draw husband = new Draw(account, 800000);
//        Draw wife = new Draw(account, 900000);
//        _5Thread t1 = new _5Thread(husband, "可悲的丈夫");
//        _5Thread t2 = new _5Thread(wife, "高兴的妻子");
//
//        t1.start();
//        t2.start();

        // 开启多个线程操作容器
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }

        System.out.println(list.size());
    }
}

/**
 * 模拟12306取票
 */
class UnsafeWeb12306 implements Runnable{
    private int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            test();
        }
    }

    public void test() {
        if (ticketNums < 0) {
            flag = false;
            return;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        /**
         * 个人理解：
         *
         * 出现相同值的原因在于，每个线程都有着自己的工作空间，并且借此和主内存进行数据交互。例如，A线程从主内存中获取了自己的票，
         * 与此同时，进行打印输出和进行数据更新，但是在这个过程还未结束的同时，B线程也从主内存中获取了自己的票，但是主内存中的数据还未被A
         * 线程更新，就导致出现了相同数据的情况。
         *
         * 出现负数的原因在于，A线程获取了最后一张票0，此时，ticketNums变为负数并更新了主内存中的数据，而在进行是否继续循环的判断时，B线程
         * 从主内存中获取了数据，此时ticketNums的值为-1，以此类推。
         */
    }
}

class Draw implements Runnable {
    private Account account;
    private int drawMoney;  // 取的钱数
    private int pocketMoney;    // 口袋里面的总数

    public Draw(Account account, int drawMoney) {
        this.account = account;
        this.drawMoney = drawMoney;
    }

    public Draw() {

    }

    @Override
    public void run() {
        if (account.getMoney() - drawMoney < 0) {
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.setMoney(account.getMoney() - drawMoney);
        pocketMoney += drawMoney;
        System.out.println(Thread.currentThread().getName() + " --> 账户余额为: " + account.getMoney());
        System.out.println(Thread.currentThread().getName() + " --> 口袋里面的总数为: " + pocketMoney);
    }
}
