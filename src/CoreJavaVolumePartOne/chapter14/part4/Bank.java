package CoreJavaVolumePartOne.chapter14.part4;/* @program: CoreJavaVolumePartOne
 * @description: A bank with a number of bank accounts that uses locks for serializing access.
 * @chineseDescription: 程序清单14-7
 * @author: LiuDongMan
 * @createdDate: 2019-07-07 21:06
 */

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    /**
     * Constructs the bank.
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();

        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }

            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f\n", getTotalBalance());
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the sum of all accounts balances.
     * @return the total balance
     */
    public double getTotalBalance() {
        bankLock.lock();

        try {
            double sum = 0;

            for (double a : accounts) {
                sum += a;
            }

            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
