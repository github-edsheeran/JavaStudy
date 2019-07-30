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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Canteen(Table table) {
        this.table = table;
    }

    public Canteen() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                String food = "烤鸭";
                System.out.println("食堂上了: " + food);
                table.serve(food);
            } else {
                String food = "炸鸡";
                System.out.println("食堂上了: " + food);
                table.serve(food);
            }
        }
    }
}

/**
 * 消费者，员工
 */
class Employee implements Runnable {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Employee(Table table) {
        this.table = table;
    }

    public Employee() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("员工享用了: " + table.eat());
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

    public synchronized String eat() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.notifyAll();
        this.flag = false;

        return this.food;
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
        this.notifyAll();
        flag = true;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Table() {
    }

    public Table(String food) {
        this.food = food;
    }
}
