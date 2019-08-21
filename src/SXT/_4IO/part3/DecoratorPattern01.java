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

    }
}

interface Say {
    void say();
}

class Person implements Say {
    private int decibel = 10;

    @Override
    public void say() {

    }
}
