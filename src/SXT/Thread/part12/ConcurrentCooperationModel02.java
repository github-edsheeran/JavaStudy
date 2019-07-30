package SXT.Thread.part12;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解并发协作模型，信号灯法
 * @author: LiuDongMan
 * @createdDate: 2019-07-30 16:19
 **/
public class ConcurrentCooperationModel02 {
    public static void main(String[] args) {
        Table table = new Table();
        new Thread(new Canteen(table)).start();
        new Thread(new Employee(table)).start();
    }
}

/**
 * 生产者，食堂
 */
class Canteen implements Runnable {
    private Table table;

    public Canteen(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                this.table.serve("烤鸭");
            } else {
                this.table.serve("炸鸡");
            }
        }
    }
}

/**
 * 消费者，员工
 */
class Employee implements Runnable {
    private Table table;

    public Employee(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            table.eat();
        }
    }
}

/**
 * 生产者和消费者需要用来交互的资源，餐桌
 */
class Table {
    /**
     * 食物
     */
    private String food;

    /**
     * T 餐桌有食物，员工享用
     * F 餐桌没食物，食堂上菜
     */
    private boolean flag = false;

    public synchronized void eat() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("员工享受了: " + this.food);
        this.notifyAll();
        this.flag = false;
    }

    public synchronized void serve(String food) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.food = food;
        System.out.println("食堂准备了: " + food);
        this.notifyAll();
        this.flag = true;
    }

    public Table() {
    }
}
