package CoreJavaVolumePartOne.chapter3;

import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates a <code>do/while</code> loop.
 * @chineseDescription: 程序清单3-4
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 10:48
 **/
public class _4Retirement2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How much money do you need to retire? ");
        double goal = in.nextDouble();

        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        double balance = 0;
        int year = 0;

        String input;

        do {
            balance += payment;
            double interest = balance * interestRate / 100;
            balance += interest;

            year++;

            System.out.printf("After year %d, your balance is %,.2f%n", year, balance);
            input = in.next();
        } while (input.equals("N"));
    }
}
