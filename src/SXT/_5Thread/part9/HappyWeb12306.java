package SXT._5Thread.part9;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 模拟12306抢票
 * @author: LiuDongMan
 * @createdDate: 2019-07-28 21:51
 */
public class HappyWeb12306 {
    public static void main(String[] args) {
        Web12306 web12306 = new Web12306(6);

        Passenger passenger01 = new Passenger("张三", web12306, 2);
        Passenger passenger02 = new Passenger("李四", web12306, 3);

        new Thread(passenger01).start();
        new Thread(passenger02).start();
    }
}

class Web12306 {
    private int availableSeats;

    public Web12306() {
    }

    public Web12306(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    /**
     * 通过同步方法来锁定对象，此时，锁定的是Web12306的对象
     * @param seats
     * @return
     */
    public synchronized boolean bookSeats(int seats) {
        System.out.println("欢迎光临12306, 火车票还剩" + availableSeats + "张!");

        if (availableSeats - seats < 0) {
            return false;
        } else {
            availableSeats -= seats;
            return true;
        }
    }
}

class Passenger implements Runnable {
    private String name;
    private Web12306 web12306;
    private int seats;

    public Passenger(String name, Web12306 web12306, int seats) {
        this.name = name;
        this.web12306 = web12306;
        this.seats = seats;
    }

    public Passenger() {
    }

    @Override
    public void run() {
        boolean flag = web12306.bookSeats(seats);

        if (flag) {
            System.out.println("客户: " + name + "预定成功!");
        } else {
            System.out.println("客户: " + name + "预定失败!");
        }
    }
}