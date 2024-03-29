package CoreJavaVolumePartOne.chapter14.part3;

/**
 * @program: JavaStudy
 * @description: This program shows data corruption when multiple threads access a data structure.
 * @chineseDescription: 程序清单14-5
 * @author: LiuDongMan
 * @createdDate: 2019-07-29 15:50
 **/
public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;

            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();

                        bank.transfer(fromAccount, toAccount, amount);

                        Thread.sleep((long) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread t = new Thread(r);
            t.start();

            /**
             * 线程不安全
             * 原因：假设有A、B两个线程，访问的都是同一个账户account，A线程从主存中读取account值到自身的工作内存中，假设值为5000，此时，
             * 转入金额为500，将值相加后，在返回给主存的过程中，B线程从主存也读取了account的值到自己的工作内存中，由于A线程的值还未返回，
             * 此时account的值依然是5000，假设转入金额为900，值相加后，先A线程一步返回到主存中，此时account值为5900，但是这时，A线程将
             * 修改之后的account返回到主存中，为5500，将B线程的值覆盖，造成数据不同步，线程不安全
             */
        }
    }
}
