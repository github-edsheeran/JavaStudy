package CoreJavaVolumePartOne.chapter14.part3;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program shows data corruption when multiple threads access a data structure.
 * @chineseDescription: 程序清单14-5
 * @author: LiuDongMan
 * @createdDate: 2019-07-04 21:04
 **/
public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;    // 转出账户

            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());    // 转入账户
                        double amount = MAX_AMOUNT * Math.random(); // 转出和转入金额
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }
            };

            Thread t = new Thread(r);
            t.start();
            /**
             * 这段代码的问题在于：当一个账户进行转款之后进行休眠的过程中，另外一个线程的账户将钱转入到这个账户中
             */
        }
    }
}
