package SXT._2BaseOfObjectOriented.part1;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解继承中，构造器、静态初始化块以及初始化块的调用顺序
 * @author: LiuDongMan
 * @createdDate: 2019-08-09 17:12
 **/
public class InheritAndStaticBlock {
    public static void main(String[] args) {
        Student student = new Student("张三", 18, "男", "1");
        /**
         * 调用Person类静态初始化块
         * 调用Student类静态初始化块
         * 调用Person类的初始化块
         * 调用Person类构造器
         * 调用Student类初始化块
         * 调用Student类构造器
         *
         * 可以看到，静态初始化块、初始化块、构造器的调用顺序是一样的，都是由父类开始往下
         */
    }
}

class Person {
    private String name;
    private int age;
    private String gender;

    {
        System.out.println("调用Person类的初始化块");
    }

    static {
        System.out.println("调用Person类静态初始化块");
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("调用Person类构造器");
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

class Student extends Person {
    private String StudentId;

    {
        System.out.println("调用Student类初始化块");
    }

    static {
        System.out.println("调用Student类静态初始化块");
    }

    public Student(String name, int age, String gender, String studentId) {
        super(name, age, gender);
        this.StudentId = studentId;
        System.out.println("调用Student类构造器");
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }
}
