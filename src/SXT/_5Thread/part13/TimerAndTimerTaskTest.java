package SXT._5Thread.part13;

import java.util.Timer;
import java.util.TimerTask;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解Timer和TimerTask类，来执行定时任务
 * @author: LiuDongMan
 * @createdDate: 2019-07-30 20:30
 */
public class TimerAndTimerTaskTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 1000);    // 一秒后执行对应的任务，还有在指定的时间执行任务，以及间隔多少秒的循环任务等，通过API自己了解
    }
}

class MyTimer extends TimerTask {

    @Override
    public void run() {
        System.out.println("放空大脑，休息一会");
    }
}
