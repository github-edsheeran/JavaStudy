package CoreJavaVolumePartOne.chapter3;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program shows how to store tabular data in a 2D array.
 * @chineseDescription: 程序清单3-8
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 15:26
 **/
public class _8CompoundInterest {
    public static void main(String[] args) {
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;

        double[] interestRate = new double[NRATES];
        for (int j = 0; j < interestRate.length; j++) {
            interestRate[j] = (STARTRATE + j) / 100.0;
        }

        double[][] balances = new double[NYEARS][NRATES];
        for (int j = 0; j < balances[0].length; j++) {
            balances[0][j] = 10000;
        }

        for (int i = 1; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {
                double oldBalance = balances[i - 1][j];
                double interest = oldBalance * interestRate[j];
                balances[i][j] = oldBalance + interest;
            }
        }

        for (int j = 0; j < interestRate.length; j++) {
            System.out.printf("%9.0f%%", 100 * interestRate[j]);
        }

        System.out.println();

        for (double[] row : balances) {
            for (double b : row) {
                System.out.printf("%10.2f", b);
            }

            System.out.println();
        }
    }
}
