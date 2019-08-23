package SXT._5Thread.part12;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解并发协作模型之一，管程法
 * @author: LiuDongMan
 * @createdDate: 2019-07-30 15:07
 **/
public class ConcurrentCooperationModel01 {
    public static void main(String[] args) {
        SynchContainer synchContainer = new SynchContainer();
        new Thread(new Producer(synchContainer)).start();
        new Thread(new Consumer(synchContainer)).start();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private SynchContainer synchContainer;

    public SynchContainer getSynchContainer() {
        return synchContainer;
    }

    public void setSynchContainer(SynchContainer synchContainer) {
        this.synchContainer = synchContainer;
    }

    public Producer(SynchContainer synchContainer) {
        this.synchContainer = synchContainer;
    }

    public Producer() {
    }

    @Override
    public void run() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("生产者生产编号为: " + i + "的小馒头存入仓库");
//            synchContainer.push(new SteamedBun(i + ""));
//        }

        int i = 0;
        while (true) {
            System.out.println("生产者生产编号为: " + i + "的小馒头存入仓库");
            synchContainer.push(new SteamedBun(i + ""));
            i++;
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    private SynchContainer synchContainer;

    public SynchContainer getSynchContainer() {
        return synchContainer;
    }

    public void setSynchContainer(SynchContainer synchContainer) {
        this.synchContainer = synchContainer;
    }

    public Consumer(SynchContainer synchContainer) {
        this.synchContainer = synchContainer;
    }

    public Consumer() {
    }

    @Override
    public void run() {
//        for (int i = 0; i < 100; i++) {
//            System.out.println("消费者消费编号为: " + synchContainer.pull().getId() + "的小馒头");
//        }
        while (true) {
            System.out.println("消费者消费编号为: " + synchContainer.pull().getId() + "的小馒头");
        }
    }
}

/**
 * 缓冲区
 */
class SynchContainer {
    private SteamedBun[] steamedBuns = new SteamedBun[10]; // 缓冲区存取的数据类型以及大小
    private int count = 0;

    /**
     * 生产者生产数据并存入缓冲区
     * @param steamedBun
     */
    public synchronized void push(SteamedBun steamedBun) {
        if (count == steamedBuns.length) {  // 之所以索引等于数组长度，是因为下面count++在存入最后一个数据的时候，其值加1，等于数组长度
            try {
                this.wait();    // 当缓冲区数据存储已满的时候，线程进入等待，直到消费者进行消费并唤醒等待线程，才继续生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        steamedBuns[count] = steamedBun;
        count++;

        this.notifyAll();   // 生产数据，唤醒消费等待线程
    }

    /**
     * 消费者消费数据并从缓冲区取出
     * @return
     */
    public synchronized SteamedBun pull() {
        if (count == 0) {
            try {
                this.wait();    // 当缓冲区没有数据时，线程进入等待，直到生产者进行生产并唤醒等待线程，才继续消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;    // 之所以索引在前并且自减，是因为存入数据的时候会进行自增操作
        SteamedBun steamedBun = steamedBuns[count];

        this.notifyAll();   // 消费数据，唤醒生产等待线程

        return  steamedBun;
    }
}

/**
 * 存取的数据，以小馒头为例
 */
class SteamedBun {
    private String id;  // 小馒头的编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SteamedBun() {
    }

    public SteamedBun(String id) {
        this.id = id;
    }
}
