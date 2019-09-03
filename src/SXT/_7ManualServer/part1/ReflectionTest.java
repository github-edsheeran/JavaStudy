package SXT._7ManualServer.part1;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解反射的作用
 * @author: LiuDongMan
 * @createdDate: 2019-09-03 15:19
 **/
public class ReflectionTest {
    public static void main(String[] args) {
        // 之前创建对象的方式
        Person person01 = new Person("张三", 20);

        /**
         * 反射的三种方式：
         * 1.对象.getClass()；
         * 2.类.class；
         * 3.类.forName(完整的包名和类名)，一般这种方式使用的最多；
         * 目前暂时先不用泛型，且需要注意，class是Java关键字，不能用作变量
         */
        Class aClass = person01.getClass();
        aClass = Person.class;
        try {
            /**
             * 可以看到，通过这种方式进行获取的话，类与类之间的耦合度降到了最低，前面两种方式获取类对象，相应的类必须存在，否则编译器不让通过，
             * 第三种方式的话，后期相应的包名和类名会通过字符串的形式进行传输，且一般传入的是子类
             */
            aClass = Class.forName("SXT._7ManualServer.part1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
