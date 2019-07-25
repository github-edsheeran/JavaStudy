package CoreJavaVolumePartOne.chapter8.part2;

import java.time.LocalDate;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-20 12:04
 **/
public class Test {
    public static void main(String[] args) {
//        DateInterval interval = new DateInterval();
//        Pair<LocalDate> pair = interval;
//        interval.setSecond(LocalDate.of(1993, 8, 9));

//        Dictionary<Integer, Component> labelTable = new Hashtable<>();
//        labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
//        labelTable.put(20, new JLabel(new ImageIcon("ten.gif")));
//        JSlider jSlider = new JSlider();
//        jSlider.setLabelTable(labelTable);

//        Pair<String> stringPair = new Pair<>();
//        Pair<Employee> employeePair = new Pair<>();
//        System.out.println(stringPair.getClass() == employeePair.getClass());


    }

    public static <T> T getMiddle(T[] ts) {
        System.out.println("泛型方法");
        return ts[ts.length / 2];
    }

    // 和上面的方法有着同样的擦除，因此冲突
//    public static Object getMiddle(Object[] objects) {
//        return objects[objects.length / 2];
//    }

    public static <T extends Comparable> T min(T[] ts) {
        if (ts == null || ts.length == 0) {
            return null;
        }

        T smallest = ts[0];

        for (int i = 1; i < ts.length; i++) {
            if (ts[0].compareTo(ts[i]) > 0) {
                smallest = ts[i];
            }
        }

        return smallest;
    }

    public static <T extends Comparable> Pair<T> minmax(T[] ts) {
        if (ts == null || ts.length == 0) {
            return null;
        }

        T min = ts[0];
        T max = ts[0];

        for (int i = 1; i < ts.length; i++) {
            if (min.compareTo(ts[i]) > 0) {
                min = ts[i];
            }
            if (max.compareTo(ts[i]) < 0) {
                max = ts[i];
            }
        }

        return new Pair<>(min, max);
    }

    // 和上面的方法有着同样的擦除，因此冲突
//    public static Pair<Comparable> minmax(Comparable[] comparables) {
//        return null;
//    }
}

class DateInterval extends Pair<LocalDate> {
    public DateInterval() {

    }

    public DateInterval(LocalDate first, LocalDate second) {
        super(first, second);
    }

    @Override
    public void setSecond(LocalDate second) {
        super.setSecond(second);
    }

    @Override
    public LocalDate getSecond() {
        return super.getSecond();
    }

    public static void main(String[] args) {
        DateInterval interval = new DateInterval(LocalDate.of(1993, 8, 9), LocalDate.of(1993, 8, 10));
        Pair<LocalDate> pair = interval;    // 超类，多态
        LocalDate localDate = LocalDate.of(1993, 8, 11);

        System.out.println("原来的日期: " + pair.getSecond());
        System.out.println("set进的新日期: " + localDate);

        pair.setSecond(localDate);

        System.out.println("执行pair.setSecond(localDate)后的日期: " + pair.getSecond());
    }
}