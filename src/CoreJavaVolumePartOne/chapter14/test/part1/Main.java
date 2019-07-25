package CoreJavaVolumePartOne.chapter14.test.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-24 22:02
 **/
public class Main {
    public static void main(String[] args) {
//        // 实现多线程有两种手段，一种是继承Thread类
//        Thread1 mTh1 = new Thread1("A");
//        Thread1 mTh2 = new Thread1("B");
//        mTh1.start();   // 注意：start方法调用后并不是立即执行多线程代码，而是使得该线程变为可运行状态，什么时候运行是由操作系统决定的。
//                        // 从程序运行的结果来看，多线程程序是乱序执行，因此，只有乱序执行的代码才有必要设计为多线程
//        mTh2.start();

        // 另一种是实现Runnable接口
        new Thread(new Thread2("C")).start();
        new Thread(new Thread2("D")).start();

        // 注：实际上所有的多线程代码都是通过运行Thread.start方法来运行的，因此，不管是扩展Thread类还是实现Runnable接口来实现多线程，最终还是通过Thread的对象的API来控制多线程，熟悉Thread类的API是进行多线程的基础


    }
}

class Thread1 extends Thread {
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行: " + i);
            try {
                sleep((int) Math.random() * 10);    // Thread.sleep方法调用的目的是不让当前线程霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
                                                    // 实际上所有的多线程代码执行顺序都是不确定的，每次执行的结果都是随机的。
                                                    // 但是start方法重复调用的话，会出现java.lang.IllegalThreadStateException异常
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 implements Runnable {
    private String name;

    public Thread2(String name) {
        this.name = name;
    }

    /**
     * run方法是多线程程序的一个约定，所有的多线程代码都在run方法里面。Thread类实际上也是实现了Runnable接口的类
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行: " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}