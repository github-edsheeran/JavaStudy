package CoreJavaVolumePartOne.chapter3;

import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates a <code>for</code> loop.
 * @chineseDescription: 程序清单3-5
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 11:01
 **/
public class _5LotteryOdds {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

        int lotteryOdds = 1;
        for (int i = 0; i <= k; i++) {
            lotteryOdds = lotteryOdds * (n - i + 1) / i;
        }

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
