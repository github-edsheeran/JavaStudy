package JueJin.Thread.part4;

/**
 * @program: Homework
 * @description:
 * @chineseDescription: 测试interrupt方法
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 14:31
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
//        for (int i = 0; i < 5000000; i++) {
//            if (this.isInterrupted()) {
//                System.out.println("已经是停止状态了!我要退出了!");
//                break;  // 经测试后发现，单单使用interrupt方法是不会终止线程的，因此需要使用return来搭配终止线程
//            }
//
//            System.out.println("i = " + (i + 1));
//        }
//
//        System.out.println("看到这句话说明线程并未终止!");

        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了!");
                return; // 还有其他停止线程的方法
            }

            System.out.println("Timer = " + System.currentTimeMillis());
        }
    }
}
