package CoreJavaVolumePartOne.chapter5.part4;

import CoreJavaVolumePartOne.chapter5.part3.Employee;

import java.util.ArrayList;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates the ArrayList class.
 * @chineseDescription: 程序清单5-11
 * @author: LiuDongMan
 * @createdDate: 2019-05-19 21:25
 **/
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));

        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
        }
    }
}
