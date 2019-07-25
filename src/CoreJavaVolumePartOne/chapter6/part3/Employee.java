package CoreJavaVolumePartOne.chapter6.part3;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单6-5
 * @author: LiuDongMan
 * @createdDate: 2019-05-25 15:27
 **/
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }

    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double bypercent) {
        double raise = salary * bypercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
