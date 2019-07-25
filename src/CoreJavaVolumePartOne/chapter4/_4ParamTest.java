package CoreJavaVolumePartOne.chapter4;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates parameter passing in java.
 * @chineseDescription: 程序清单4-4
 * @author: LiuDongMan
 * @createdDate: 2019-05-13 11:23
 **/
public class _4ParamTest {
    public static void main(String[] args) {
        // test.LambdaAndMethodReference.Test 1: Methods can't modify numeric parameters
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before: percent=" + percent);
        tripleValue(percent);
        System.out.println("After: percent=" + percent);

        // Test2: Methods can change the state of object parameters
        System.out.println("\nTesting tripleSalary:");
        EmployeeParam harry = new EmployeeParam("Harry", 50000);
        System.out.println("Before: salary=" + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary=" + harry.getSalary());

        // Test3: Methods can't attach new objects to object parameters
        System.out.println("\nTesting swap:");
        EmployeeParam a = new EmployeeParam("Alice", 70000);
        EmployeeParam b = new EmployeeParam("Bob", 60000);
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
        swap(a, b);
        System.out.println("After: a=" + a.getName());
        System.out.println("After: b=" + b.getName());
    }

    public static void tripleValue(double x) {
        x = 3 * x;
        System.out.println("End of method: x=" + x);
    }

    public static void tripleSalary(EmployeeParam x) {
        x.raiseSalary(200);
        System.out.println("End of method: salary=" + x.getSalary());
    }

    public static void swap(EmployeeParam x, EmployeeParam y) {
        EmployeeParam temp = x;
        x = y;
        y = temp;
        System.out.println("End of method: x=" + x.getName());
        System.out.println("End of method: y=" + y.getName());
    }

    public void test(int i, double a) {

    }

    public void test(double a, int i) {

    }
}

class EmployeeParam {
    private String name;
    private double salary;

    public EmployeeParam(String n, double s) {
        name = n;
        salary = s;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
