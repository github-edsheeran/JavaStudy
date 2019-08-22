package SXT._4IO.part3;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 装饰器设计模式例子一
 * @author: LiuDongMan
 * @createdDate: 2019-08-21 17:02
 **/
public class DecoratorPattern01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.say();
        Amplifier amplifier = new Amplifier(person);
        amplifier.say();
    }
}

interface Say {
    void say();
}

class Person implements Say {
    private int decibel = 10;

    @Override
    public void say() {
        System.out.println("人的声音为: " + this.decibel + "分贝");
    }

    public int getDecibel() {
        return decibel;
    }

    public void setDecibel(int decibel) {
        this.decibel = decibel;
    }
}

class Amplifier implements Say {
    private Person person;

    public Amplifier(Person person) {
        this.person = person;
    }

    @Override
    public void say() {
        System.out.println("放大之后人的声音为: " + person.getDecibel() * 10 + ", 变成了噪音");
    }
}
