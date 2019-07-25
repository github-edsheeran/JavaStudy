package CoreJavaVolumePartOne.chapter4;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单4-1
 * @author: LiuDongMan
 * @createdDate: 2019-04-04 11:02
 **/
public class _1CalendarTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();   // 获取月份数
        int today = date.getDayOfMonth();   // 获取今天是几号

        date = date.minusDays(today - 1);   // 返回一个减去指定天数的date的副本，以5月份为例，此时赋值之后date为2019-05-01
        DayOfWeek weekday = date.getDayOfWeek();    // 获取当月第一天是星期几
        int value = weekday.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }

        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }

            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }

        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}
