package SXT.Thread.part2;

import java.util.concurrent.*;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 了解下另一种创建线程的方式：Callable接口
 * @author: LiuDongMan
 * @createdDate: 2019-07-18 22:47
 */
public class CRacer implements Callable<Integer> {
    private String winner;

    @Override
    public Integer call() throws Exception {
        for (int steps = 0; steps < 100; steps++) {
            if ("pool-1-thread-2".equals(Thread.currentThread().getName())) {
                Thread.sleep(100);
            }

            System.out.println(Thread.currentThread().getName() + "->" + steps);

            boolean flag = gameOver(steps);

            if (flag) {
                return steps;
            }
        }

        return null;
    }

    private boolean gameOver(int steps) {
        if (winner != null && winner.length() > 0) {
            return true;
        } else {
            if (steps == 99) {
                winner = Thread.currentThread().getName();
                System.out.println("The winner is " + winner);
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CRacer cRacer = new CRacer();

        // 创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(2);

        // 提交执行
        Future<Integer> result01 = service.submit(cRacer);
        Future<Integer> result02 = service.submit(cRacer);

        // 获取结果
        Integer r1 = result01.get();
        Integer r2 = result02.get();

        // 关闭服务
        service.shutdownNow();
    }
}
