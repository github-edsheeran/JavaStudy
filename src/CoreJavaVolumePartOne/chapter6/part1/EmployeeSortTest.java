package CoreJavaVolumePartOne.chapter6.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates the use of the Comparable interface.
 * @chineseDescription: 程序清单6-1
 * @author: LiuDongMan
 * @createdDate: 2019-05-20 20:52
 **/
public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[4];
        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);
        staff[3] = new Manager("Test", 50000);

//        Arrays.sort(staff);
//
//        for (Employee e : staff) {
//            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
//        }
        staff[0].compareTo(staff[3]);
        staff[3].compareTo(staff[0]);
    }
}
