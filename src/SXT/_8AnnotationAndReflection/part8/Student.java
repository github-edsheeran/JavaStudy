package SXT._8AnnotationAndReflection.part8;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-09-19 08:55
 **/
@Author(name = "LiuDongMan", year = 2019)
public class Student implements Person {
    private String id;
    private String name;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello(int age) {
        System.out.println("Hello, boys and girls! I'm " + age + " years old~");
    }
}
