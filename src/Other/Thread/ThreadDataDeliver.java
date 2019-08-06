package Other.Thread;

import java.util.Random;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解通过回调函数给线程进行数据传递
 * @author: LiuDongMan
 * @createdDate: 2019-08-06 20:48
 */
public class ThreadDataDeliver implements Runnable {
    private Work work;

    public ThreadDataDeliver(Work work) {
        this.work = work;
    }

    @Override
    public void run() {
        Random random = new Random();
        Data data = new Data();

        int n1 = random.nextInt();
        int n2 = random.nextInt();
        int n3 = random.nextInt();

        work.process(data, new int[]{n1, n2, n3});

        System.out.println(n1 + " + " + n2 + " + " + n3 + " = " + data.getValue());
    }

    public static void main(String[] args) {
        new Thread(new ThreadDataDeliver(new Work())).start();
    }
}

class Data {
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class Work {
    public void process(Data data, int[] numbers) {
        for (int n : numbers) {
            data.setValue(data.getValue() + n);
        }
    }
}

