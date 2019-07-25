package CoreJavaVolumePartOne.chapter3;

import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates a <code>while</code> loop.
 * @chineseDescription: 程序清单3-3
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 10:40
 **/
public class _3Retirement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How much money do you need to retire? ");
        double goal = in.nextDouble();

        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        double balance = 0;
        int years = 0;

        while (balance < goal) {
            balance += payment;
            double interest = balance * interestRate / 100;
            balance += interest;
            years++;
        }

        System.out.println("You can retire in " + years + " years.");
    }
}
