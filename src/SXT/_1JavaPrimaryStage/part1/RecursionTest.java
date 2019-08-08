package SXT._1JavaPrimaryStage.part1;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解递归，可以自己利用草稿进行手动演示，需要注意的是，任何能用递归解决的问题也能使用迭代解决。当递归方法可以更
 * 加自然地反映问题，并且易于理解和调试，并且不强调效率问题时，可以采用递归；在要求高性能的情况下尽量避免使用递归，递归调用既花时间又耗内存
 * @author: LiuDongMan
 * @createdDate: 2019-08-08 09:06
 **/
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(recursionFunction01(100));
        System.out.println(recursionFunction02(10));
    }

    /**
     * 利用递归实现1到100的和
     * @param n
     * @return
     */
    public static int recursionFunction01(int n) {
        // 定义递归头，即什么时候不调用自身方法，也就是递归的结束条件
        if (n == 1) {
            return 1;
        } else {    // 定义递归体，即什么时候需要调用自身方法
            return n + recursionFunction01(n - 1);
        }
    }

    public static long recursionFunction02(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * recursionFunction02(n - 1);
        }
    }
}
