import java.io.IOException;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 测试用的类
 * @author: LiuDongMan
 * @createdDate: 2019-07-29 10:55
 **/
public class Test {
    public static void main(String[] args) {
        MyThread01 myThread01 = new MyThread01();
        Thread thread01 = new Thread(myThread01);
        thread01.setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.println("UncaughtExceptionHandler catch a Exception");
            System.out.println(throwable.getMessage());
        });

        thread01.start();
    }
}

class MyThread01 implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable run");
        int i = 1 / 0;
    }
}
