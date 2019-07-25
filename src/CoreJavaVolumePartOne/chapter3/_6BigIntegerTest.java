package CoreJavaVolumePartOne.chapter3;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program uses big numbers to compute the odds of winning the grand prize in a lottery.
 * @chineseDescription: 程序清单3-6
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 14:32
 **/
public class _6BigIntegerTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

        BigInteger lotteryOdds = BigInteger.valueOf(1);

        for (int i = 0; i <= k; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
