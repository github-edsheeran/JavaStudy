package SXT._5Thread.part1;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 模拟12306抢票
 * @author: LiuDongMan
 * @createdDate: 2019-07-16 21:35
 */
public class Web12306 implements Runnable{
    private int ticketNums = 99;

    @Override
    public void run() {
        while (true) {
            if (ticketNums < 0) {
                break;
            }

            try {
                Thread.sleep(200);  // 当前正在执行的线程进行休眠，手动模拟网络延迟，这个时候的多线程存在着数据不安全的问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    public static void main(String[] args) {
        // 一份资源，多个代理
        Web12306 web = new Web12306();

        // 三个线程，存在着抢同一张票和票数为负数的情况，简单理解就是三个同时运行的run方法以及99张票
        new Thread(web, "张三").start();
        new Thread(web, "李四").start();
        new Thread(web, "王五").start();
    }
}
