package CoreJavaVolumePartOne.chapter5.part2;

import java.time.LocalDate;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-6
 * @author: LiuDongMan
 * @createdDate: 2019-05-18 10:57
 **/
public class Employee extends Person {
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $%.2f", salary);
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return super.toString() + "[salary=" + salary + ", hireDay=" + hireDay + "]";
    }
}
