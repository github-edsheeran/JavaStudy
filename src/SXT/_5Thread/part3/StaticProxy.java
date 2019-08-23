package SXT._5Thread.part3;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 初步了解静态代理，一般情况下分为真实角色和代理角色，并且实现同一个接口才能进行代理行为
 * @author: LiuDongMan
 * @createdDate: 2019-07-19 22:31
 */
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
    }
}

interface Marray {
    void happyMarry();
}

/**
 * 真实角色
 */
class You implements Marray {

    @Override
    public void happyMarry() {
        System.out.println("你和嫦娥本月了!");
    }
}

/**
 * 代理角色
 */
class WeddingCompany implements Marray {
    private Marray target;

    public WeddingCompany() {

    }

    public WeddingCompany(Marray target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("布置猪窝!");
    }

    private void after() {
        System.out.println("闹玉兔!");
    }
}
