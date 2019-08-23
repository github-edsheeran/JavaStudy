package SXT._5Thread.part8;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 模拟银行取钱
 * @author: LiuDongMan
 * @createdDate: 2019-07-25 22:20
 */
public class Account {
    private int money;
    private String name;

    public Account(int money) {
        this.money = money;
    }

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
