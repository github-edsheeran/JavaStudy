package CoreJavaVolumePartOne.chapter3;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates a triangular array.
 * @chineseDescription: 程序清单3-9
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 15:40
 **/
public class _9LotteryArray {
    public static void main(String[] args) {
        final int NMAX = 10;

        // Allocate triangular array
        int[][] odds = new int[NMAX + 1][];
        for (int n = 0; n <= NMAX; n++) {
            odds[n] = new int[n + 1];
        }

        // Fill triangular array
        for (int n = 0; n < odds.length; n++) {
            for (int k = 0; k < odds[n].length; k++)
            {
                /*
                 * Compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
                 */
                int lotteryOdds = 1;
                for (int i = 1; i <= k; i++) {
                    lotteryOdds = lotteryOdds * (n - i + 1) / i;
                }

                odds[n][k] = lotteryOdds;
            }
        }

        // Print triangular array
        for (int[] row : odds)
        {
            for (int odd : row) {
                System.out.printf("%4d", odd);
            }
            System.out.println();
        }
    }
}
