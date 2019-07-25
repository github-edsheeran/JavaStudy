package CoreJavaVolumePartOne.chapter4;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates static methods.
 * @chineseDescription: 程序清单4-3
 * @author: LiuDongMan
 * @createdDate: 2019-05-13 10:42
 **/
public class _3StaticTest {
    public static void main(String[] args) {
        EmployeeMain[] staff = new EmployeeMain[3];

        staff[0] = new EmployeeMain("Tom", 40000);
        staff[1] = new EmployeeMain("Dick", 60000);
        staff[2] = new EmployeeMain("Harry", 65000);

        for (EmployeeMain e : staff) {
            e.setId();
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
        }

        int n = EmployeeMain.getNextId();
        System.out.println("Next available id=" + n);
    }
}

class EmployeeMain {
    private static int nextId = 1;
    private String name;
    private double salary;
    private int id;

    public EmployeeMain(String n, double s) {
        name = n;
        salary = s;
        id = 0;
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

    public void setId() {
        id = nextId;
        nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

//    public static void main(String[] args) {
//        Employee e = new Employee("Harry", 50000);
//        System.out.println(e.getName() + " " + e.getSalary());
//    }
}
