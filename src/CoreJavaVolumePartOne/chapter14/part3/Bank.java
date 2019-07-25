package CoreJavaVolumePartOne.chapter14.part3;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: CoreJavaVolumePartOne
 * @description: A bank with a number of bank accounts.
 * @chineseDescription: 程序清单14-6
 * @author: LiuDongMan
 * @createdDate: 2019-07-04 21:04
 **/
public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();    // ReentrantLock implements the Lock interface

    /**
     * Constructs the bank.
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfer money from one account to another.
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) {
//        if (accounts[from] < amount) {
//            return;
//        }

        bankLock.lock();

        try {
            System.out.print(Thread.currentThread());

            accounts[from] -= amount;

            System.out.printf(" %10.2f from %d to %d", amount, from, to);

            accounts[to] += amount;

            System.out.printf(" Total Balance: %10.2f\n", getTotalBalance());
        } finally {
            bankLock.unlock();
        }

    }

    /**
     * Gets the sum of all account balances.
     * @return the total balance
     */
    public double getTotalBalance() {
        double sum = 0;

        for (double a : accounts) {
            sum += a;
        }

        return sum;
    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
