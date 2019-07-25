package test.innerClass.anonymousInnerClass;

import sun.security.krb5.internal.crypto.Des;


/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-13 22:42
 **/
public class TestAnonymousInnerClass {
    public static void main(String[] args) {

    }
}

class Parcel6 {
//    class MyContents implements Contents {
//        private int i = 11;
//
//        @Override
//        public int value() {
//            return i;
//        }
//    }

    public Contents contents() {
        return new Contents() {
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
        };

//        return () -> {
//            int i = 11;
//            return i;
//        };

//        class MyContents implements Contents {
//            private int i = 11;
//
//            @Override
//            public int value() {
//                return i;
//            }
//        }
//
//        return new MyContents();

    }

    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        Contents contents = parcel6.contents();
        System.out.println(contents.value());
    }
}

class Wrapping {
    private int i;

    public Wrapping(int x) {
        i = x;
    }

    public int value() {
        return i;
    }
}

class Parcel7 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            @Override
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        Wrapping wrapping = parcel7.wrapping(10);
    }
}

class Parcel8 {

    /**
     * Argument must be final to use inside anonymous inner class
     * @param dest
     * @return
     */
    public Destination destination(/*final */String dest) {   // 在JDK8之前，会强制提示加上final，现在的话编译器允许这种写法，但是编译的时候，依然是final的
        return new Destination() {
            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        Destination destination = parcel8.destination("Tasmania");
        System.out.println(destination.readLabel());
    }
}

abstract class Base {
    public Base(int i) {
        System.out.println("Base constructor, i = " + i);
    }

    public abstract void f();
}

class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Inside instance initializer");
            }

            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}

class Parcel9 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;

            // Instance initialization for each object
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();
        Destination destination = parcel9.destination("Tasmania", 101.395F);
    }
}

/**
 * Nested classes can access all members of all levels of the classes they are nested within.
 */
class MNA {
    private void f() {

    }

    class A {
        private void g() {

        }

        public class B {
            void h() {
                g();
                f();
            }
        }

    }

}

class MultiNestingAccess {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}

/**
 * An inner class cannot be overriden like a method
 */
class Egg {
    private Yolk y;

    protected class Yolk {
        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }

    public Egg() {
        System.out.println("New Egg()");
        y = new Yolk();
    }
}

class BigEgg extends Egg {
    public class Yolk {
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}

/**
 * Proper inheritance of an inner class.
 */
class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    private Yolk y = new Yolk();    // 注意这个地方！

    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    public void g() {
        y.f();
    }
}

class BigEgg2 extends Egg2 {
    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        @Override
        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 egg2 = new BigEgg2();
        egg2.g();
        /**
         * New Egg2()
         * Egg2.Yolk()
         * BigEgg2.Yolk()
         * BigEgg2.Yolk.f()
         */
    }
}

/**
 * Inheriting an inner class
 */
class WithInner {
    class Inner {
        Inner() {
            System.out.println("This is a constructor in WithInner.Inner");
        }
    }
}

class InheritInner extends WithInner.Inner {
    // ! InheritInner() {} // Won't compile
    InheritInner(WithInner wi) {
        wi.super();
        System.out.println("This is a constructor in InheritInner");
    }


    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
