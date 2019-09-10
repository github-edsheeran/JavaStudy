package SXT._8AnnotationAndReflection.part2;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 数据库对应的实体类
 * @author: LiuDongMan
 * @createdDate: 2019-09-09 15:45
 **/
@Table(value = "STUDENT")
public class Student {
    @MyField(columnName = "NAME", type = "VARCHAR2", length = 10)
    private String name;
    @MyField(columnName = "AGE", type = "DECIMAL", length = 3)
    private int age;
    @MyField(columnName = "ID", type = "VARCHAR2", length = 36)
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
