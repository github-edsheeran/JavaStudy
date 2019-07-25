package test.innerClass.localInnerClass;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-13 14:39
 **/
public class TestLocalInnerClass {
    public static void main(String[] args) {

    }
}

class Outer {
    private int s = 100;
    private int out_i = 1;

    public void f(final int k) {
        final int s = 200;
        int i = 1;
        final int j = 10;

        /**
         * 在方法中定义的内部类称为局部内部类。与局部变量类似，局部内部类不能有访问说明符，因为它不是外围类的一部分，但是它可以访问当前代码块内的常量，和此外围类所有的成员。
         */
        class Inner {
            int s = 300;    // 可以定义与外部类同名的变量

            // static int m = 20;   // 不可以定义静态变量
            Inner(int k) {
                inner_f(k);
            }

            int inner_i = 100;

            void inner_f(int k) {
                System.out.println(out_i);  // 如果内部类没有与外部类同名的变量，在内部类中可以直接访问外部类的实例变量
                System.out.println(j);  // 可以访问外部类的局部变量(即方法内的变量)，但是变量必须是final的，不过在JDK8之后，允许不加final，虽然编译之后依然是final
                System.out.println(i);
                System.out.println(s);  // 如果内部类中有与外部类同名的变量，直接用变量名访问的是内部类的变量
                System.out.println(this.s); // 用this.变量名访问的也是内部类变量
                System.out.println(Outer.this.s);   // 用外部类名.this.内部类变量名访问的是外部类变量
            }
        }

        new Inner(k);
    }

    public static void main(String[] args) {
        // 访问局部内部类必须先有外部类对象
        Outer outer = new Outer();
        outer.f(3); // 执行顺序：外部类对象调用方法f，运行到new Inner(k)的时候生成了局部内部类对象，此时局部内部类的构造器被调用，调用方法inner_f(k)
    }
}