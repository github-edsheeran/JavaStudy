package SXT._5Thread.part8;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 线程安全：在并发时保证数据的正确性，效率尽可能高
 * synchronized
 * 1.同步方法
 * 2.同步块
 * @author: LiuDongMan
 * @createdDate: 2019-07-25 22:30
 */
public class SafeThreadTest {
    public static void main(String[] args) {
//        SafeWeb12306 ex01 = new SafeWeb12306();
//        new Thread(ex01, "码畜").start();
//        new Thread(ex01, "码农").start();
//        new Thread(ex01, "码蝗").start();

        Account account = new Account(1000000, "结婚礼金");
        SafeDraw husband = new SafeDraw(account, 800000);
        SafeDraw wife = new SafeDraw(account, 900000);

        new Thread(husband, "可悲的丈夫").start();
        new Thread(wife, "高兴的媳妇").start();
    }
}

class SafeWeb12306 implements Runnable {
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

            test();
        }
    }

    // 当方法运行结束后，线程就会释放掉当前对象的锁，给其他线程提供机会
    public synchronized void test() {
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

class SafeDraw implements Runnable {
    private Account account;
    private int drawMoney;  // 取的钱数
    private int pocketMoney;    // 口袋里面的总数

    public SafeDraw(Account account, int drawMoney) {
        this.account = account;
        this.drawMoney = drawMoney;
    }

    public SafeDraw() {

    }

    @Override
    public void run() {
        test();
    }

    /**
     * 通过运行可以发现，加入synchronized关键字之后依然出现线程不安全的问题，原因在于目标不对，因此造成锁定失败。这个地方，锁定的不应该是
     * this，而应该是account对象才行
     * 个人理解：因为是启动了两个线程，因此，synchronized关键字锁的都是各自的对象，account对象在两个线程中同时被修改，出现线程不安全的情况
     */
    public synchronized void test() {
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