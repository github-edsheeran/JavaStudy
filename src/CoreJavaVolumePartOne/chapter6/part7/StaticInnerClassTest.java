package CoreJavaVolumePartOne.chapter6.part7;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates the use of static inner classes.
 * @chineseDescription: 程序清单6-9
 * @author: LiuDongMan
 * @createdDate: 2019-06-15 09:59
 **/
public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];

        for (int i = 0; i < d.length; i++) {
            d[i] = 100 * Math.random();
        }

        ArrayAlg.Pair pair = ArrayAlg.minmax(d);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}

class ArrayAlg {

    /**
     * A pair of floating-point numbers
     */
    public static class Pair {
        private double first;
        private double second;

        public Pair(double f, double s) {
            first = f;
            second = s;
        }

        public double getSecond() {
            return second;
        }

        public double getFirst() {
            return first;
        }
    }

    public static Pair minmax(double[] values) {
        double min = Double.NEGATIVE_INFINITY;
        double max = Double.POSITIVE_INFINITY;

        for (double v : values) {
            if (min > v) {
                min = v;
            }
            if (max < v) {
                max = v;
            }
        }

        return new Pair(min, max);
    }
}
