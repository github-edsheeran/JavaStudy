package CoreJavaVolumePartOne.chapter7.part1;

import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: A program that displays a trace feature of a recursive method call.
 * @chineseDescription: 程序清单7-1
 * @author: LiuDongMan
 * @createdDate: 2019-06-16 17:16
 **/
public class StackTraceTest {
    /**
     * Computes the factorial of a number
     * @param n
     * @return
     */
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "): ");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();

        for (StackTraceElement f : frames) {
            System.out.println(f);
        }

        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }

        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = in.nextInt();
        factorial(n);
    }
}
