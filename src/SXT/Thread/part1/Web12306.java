package SXT.Thread.part1;
/* @program: SXT
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
                Thread.sleep(200);  // 手动模拟网络延迟，这个时候的多线程存在着数据不安全的问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    public static void main(String[] args) {
        Web12306 web = new Web12306();
        new Thread(web, "张三").start();
        new Thread(web, "李四").start();
        new Thread(web, "王五").start();
    }
}
