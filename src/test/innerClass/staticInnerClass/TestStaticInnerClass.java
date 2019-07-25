package test.innerClass.staticInnerClass;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-13 21:59
 **/
public class TestStaticInnerClass {
    public static void main(String[] args) {

    }
}

class Outer {
    private static int i = 1;
    private int j = 10;

    public static void outer_f1() {

    }

    public void outer_f2() {

    }

    /**
     * 静态内部类可以用public、protected、private修饰，可以定义静态或者非静态的成员
     */
    static class Inner {
        static int inner_i = 100;
        int inner_j = 200;

        static void inner_f1() {
            System.out.println("Outer.i" + i);  // 静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
            outer_f1();
        }

        void inner_f2() {
            //System.out.println("Outer.i" + j);    // 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)，个人理解：有静态内部类的实例但不一定有外部类的实例，这个时候外部类的非静态成员还不存在，因此不能访问
            //outer_f2();
        }
    }

    public void outer_f3() {
        System.out.println(Inner.inner_i);  // 外部类访问内部类的静态成员：内部类.静态成员
        Inner.inner_f1();

        Inner inner = new Inner();  // 外部类访问内部类的非静态成员：实例化内部类即可
        inner.inner_f2();
    }

    public static void main(String[] args) {
        new Outer().outer_f3();
    }
}