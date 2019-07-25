package CoreJavaVolumePartOne.chapter5.part1;

import java.time.LocalDate;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-2
 * @author: LiuDongMan
 * @createdDate: 2019-05-16 17:13
 **/
public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public Employee test() {
        return null;
    }
}
