package CoreJavaVolumePartOne.chapter3;

import java.util.Scanner;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates console input.
 * @chineseDescription: 程序清单3-2
 * @author: LiuDongMan
 * @createdDate: 2019-04-03 10:23
 **/
public class _2InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("What is your name? ");
        String name = in.nextLine();

        System.out.print("How old are you? ");
        int age = in.nextInt();

        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
    }
}
