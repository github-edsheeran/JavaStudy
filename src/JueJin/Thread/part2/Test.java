package JueJin.Thread.part2;

/**
 * @program: Homework
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 11:17
 **/
public class Test {
    public static void main(String[] args) {
        Thread a = new MyThread("A");
        Thread b = new MyThread("B");
        Thread c = new MyThread("C");
        a.start();
        b.start();
        c.start();
    }
}
