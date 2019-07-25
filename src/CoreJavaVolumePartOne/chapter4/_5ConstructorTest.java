package CoreJavaVolumePartOne.chapter4;

import java.util.Random;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates object construction.
 * @chineseDescription: 程序清单4-5
 * @author: LiuDongMan
 * @createdDate: 2019-05-13 12:35
 **/
public class _5ConstructorTest {
    public static void main(String[] args) {
        EmployeeConstructor[] staff = new EmployeeConstructor[3];
        staff[0] = new EmployeeConstructor("Harry", 40000);
        staff[1] = new EmployeeConstructor(60000);
        staff[2] = new EmployeeConstructor();

        for (EmployeeConstructor e : staff) {
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
        }
    }
}

class EmployeeConstructor {
    private static int nextId;
    private int id;
    private String name = "";
    private double salary;

    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    {
        id = nextId;
        nextId++;
    }

    public EmployeeConstructor(String n, double s) {
        name = n;
        salary = s;
    }

    public EmployeeConstructor(double s) {
        this("Employee #" + nextId, s);
    }

    public EmployeeConstructor() {
        // name initialized to ""--see above
        // salary not explicitly set--initialized to 0
        // id initialized in initialization block
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
