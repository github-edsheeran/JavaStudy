package test.innerClass.memberInnerClass;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-13 10:57
 **/
public class TestMemberInnerClass {
    public static void main(String[] args) {

    }
}

class Outer {
    private static int i = 1;
    private int j = 10;
    private int k = 20;

    public static void outer_f1() {

    }

    public void outer_f2() {

    }

    /**
     * 成员内部类中，不能定义静态成员，因为在加载外部类的时候并不会加载它，所以它里面不能有静态变量或者静态方法
     */
    class Inner {
        //static int inner_i = 100; // 内部类中不允许定义静态变量
        int j = 100;    // 内部类和外部类的实例变量可以共存
        int inner_i = 1;

        void inner_f1() {
            System.out.println(i);  // 在内部类中访问内部类自己的变量直接用变量名
            System.out.println(j);
            System.out.println(this.j); // 在内部类中访问内部类自己的变量也可以用this.变量名
            System.out.println(Outer.this.j);// 在内部类中访问外部类中与内部类同名的实例变量用外部类名.this.变量名
            System.out.println(k);  // 如果内部类中没有与外部类同名的变量，则可以直接用变量名访问外部类变量
            outer_f1();
            outer_f2();
        }
    }

    /**
     * 外部类的非静态方法访问成员内部类
     */
    public void outer_f3() {
        Inner inner = new Inner();
        inner.inner_f1();
    }

    /**
     * 外部类的静态方法访问成员内部类，与在外部类外部访问成员内部类一样
     */
    public static void outer_f4() {
        Outer outer = new Outer();  // Step1 建立外部类对象，成员内部类依附于外部类
        Inner inner = outer.new Inner();    // Step2 根据外部类对象建立内部类对象
        inner.inner_f1();   // Step3 访问内部类的方法
    }

    public static void main(String[] args) {
        outer_f4(); // 该语句的输出结果和下面几条语句的输出结果一样

        // 如果要直接创建内部类的对象，不能想当然地认为只需加上外围类Outer的名字，就可以按照通常的样子生成内部类的对象，而是必须使用此外围类的一个对象来创建其内部类的一个对象：
        Outer.Inner inner = new Outer().new Inner();

        // 因此，除非你已经有了外围类的一个对象，否则不可能生成内部类的对象。因为此内部类的对象会悄悄地链接到创建它的外围类的对象。如果你用的是静态的内部类，那就不需要对其外围类对象的引用。
        Outer outer = new Outer();
        Outer.Inner outin = outer.new Inner();
        outin.inner_f1();

        //注意：内部类是一个编译时的概念，一旦编译成功，就会成为完全不同的两类。对于一个名为outer的外部类和其内部定义的名为inner的内部类。编译完成后出现outer.class和outer$inner.class两类。
    }
}
