package SXT.Thread.part6;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解线程的所有状态
 * @author: LiuDongMan
 * @createdDate: 2019-07-22 21:55
 */
public class AllStates {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("...");


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getState());  // 线程创建，NEW状态

        thread.start();

//        while (thread.getState() != Thread.State.TERMINATED) {
//            System.out.println(thread.getState());
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        while (true) {
            int threadActiveCount = Thread.activeCount();

            System.out.println("线程数: " + threadActiveCount);

            if (threadActiveCount == 1) {    // 获取线程数，在这个地方，如果线程数等于1，则证明只剩下主线程，因此跳出循环
                break;
            }

            System.out.println(thread.getState());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(thread.getState());
    }
}
