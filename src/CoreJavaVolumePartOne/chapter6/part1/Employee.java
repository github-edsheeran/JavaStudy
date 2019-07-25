package CoreJavaVolumePartOne.chapter6.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单6-2
 * @author: LiuDongMan
 * @createdDate: 2019-05-20 20:53
 **/
public class Employee implements Comparable<Employee> {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
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

    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary, o.salary);
    }
}
