package SXT._5Thread.part9;

import java.util.ArrayList;
import java.util.List;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 模拟电影院选票
 * @author: LiuDongMan
 * @createdDate: 2019-07-28 16:34
 */
public class HappyCinema {
    public static void main(String[] args) {
        // 影院中的可用位置
        List<Integer> cinemaAvailableSeats = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(5);
            add(6);
            add(7);
        }};

        Cinema cinema = new Cinema(cinemaAvailableSeats, "万达影院");

        // 顾客预定的位置
        List<Integer> seats01 = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};

        List<Integer> seats02 = new ArrayList<Integer>(){{
            add(5);
            add(6);
            add(7);
        }};

        Customer customer01 = new Customer("张三", cinema, seats01);
        Customer customer02 = new Customer("李四", cinema, seats02);

        new Thread(customer01).start();
        new Thread(customer02).start();
    }
}

class Customer implements Runnable {
    private String name;
    private Cinema cinema;
    private List<Integer> seats;

    public Customer(String name, Cinema cinema, List<Integer> seats) {
        this.name = name;
        this.cinema = cinema;
        this.seats = seats;
    }

    public Customer() {
    }

    @Override
    public void run() {
        boolean flag = true;

        // 重点在于要锁住正确的对象
        synchronized (cinema) {
            flag = cinema.bookSeats(seats);
        }

        if (flag) {
            System.out.println("出票成功! " + this.name + ". 位置号为: " + seats);
        } else {
            System.out.println("出票失败! " + this.name + "位置不够...");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}

class Cinema {
    private List<Integer> availableSeats;   // 可用位置
    private String name;    // 影院名

    public Cinema() {
    }

    public Cinema(List<Integer> availableSeats, String name) {
        this.availableSeats = availableSeats;
        this.name = name;
    }

    public boolean bookSeats(List<Integer> seats) {
        System.out.println("欢迎光临" + this.name + ", 目前可用的位置为: " + availableSeats);

        // 对可用位置进行备份，后面会用到，不能使用引用，因为指向的都是同一个对象
        List<Integer> copy = new ArrayList<>();
        copy.addAll(availableSeats);

        copy.removeAll(seats);

        /**
         * 如果订票失败，则可用位置不能变
         */
        if (availableSeats.size() - copy.size() != seats.size()) {
            return false;
        }

        // 如果订票成功，则把减去之后的位置给到可用位置
        availableSeats = copy;
        return true;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Integer> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
