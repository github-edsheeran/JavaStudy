package SXT.Thread.part2;

/* @program: SXT
 * @description:
 * @chineseDescription: 模拟龟兔赛跑
 * @author: LiuDongMan
 * @createdDate: 2019-07-18 22:25
 */
public class Racer implements Runnable{
    private static String winner;

    @Override
    public void run() {
        for (int steps = 0; steps < 100; steps++) {
            // 模拟龟兔赛跑游戏中兔子休息，每十步休息一次
            if ("兔子".equals(Thread.currentThread().getName()) && steps % 10 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "-->" + steps);

            boolean flag = gameOver(steps);

            if (flag) {
                break;
            }
        }
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

    public static void main(String[] args) {
        Racer racer = new Racer();

        new Thread(racer, "乌龟").start();
        new Thread(racer, "兔子").start();
    }
}
