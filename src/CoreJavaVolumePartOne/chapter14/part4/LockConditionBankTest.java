package CoreJavaVolumePartOne.chapter14.part4;

/**
 * @program: JavaStudy
 * @description: This program shows thread synchronization when multiple threads access a data structure.
 * @chineseDescription: 简单理解线程安全
 * @author: LiuDongMan
 * @createdDate: 2019-07-29 16:30
 **/
public class LockConditionBankTest {
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
        }
    }
}
