package SXT._4IO.part3;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 装饰器设计模式例子二，模拟冲调咖啡
 * 1.抽象组件：需要装饰的抽象对象（接口或抽象父类）
 * 2.具体组件：需要装饰的对象
 * 3.抽象装饰类：包含对抽象组件的引用以及装饰者共有的方法
 * 4.具体装饰类：装饰具体组件
 * @author: LiuDongMan
 * @createdDate: 2019-08-22 09:41
 **/
public class DecoratorPattern02 {
    public static void main(String[] args) {
        Drink coffee = new Coffee();
        Drink sugar = new Sugar(coffee);    // 装饰
        System.out.println(sugar.info() + " --> " + sugar.cost());
        Drink milk = new Milk(coffee);  // 装饰
        System.out.println(milk.info() + " --> " + milk.cost());
        milk = new Milk(sugar); // 装饰
        System.out.println(milk.info() + " --> " + milk.cost());
    }
}

/**
 * 抽象组件
 */
interface Drink {
    double cost();  // 费用
    String info();  // 说明
}

/**
 * 具体组件
 */
class Coffee implements Drink {
    private String name = "原味咖啡";

    @Override
    public double cost() {
        return 10;
    }

    @Override
    public String info() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 抽象装饰类
 */
abstract class Decorator implements Drink {
    // 对抽象组件的引用，这样装饰类就可以装饰任何具体组件
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double cost() {
        return this.drink.cost();
    }

    @Override
    public String info() {
        return this.drink.info();
    }
}

/**
 * 具体装饰类
 */
class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost() * 4;
    }

    @Override
    public String info() {
        return super.info() + "加入了牛奶";
    }
}

class Sugar extends Decorator {
    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost() * 2;
    }

    @Override
    public String info() {
        return super.info() + "加入了蔗糖";
    }
}
