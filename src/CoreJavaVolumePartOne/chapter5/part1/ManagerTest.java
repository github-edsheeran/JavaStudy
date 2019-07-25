package CoreJavaVolumePartOne.chapter5.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates inheritance.
 * @chineseDescription: 程序清单5-1
 * @author: LiuDongMan
 * @createdDate: 2019-05-16 17:12
 **/
public class ManagerTest {
    public static void main(String[] args) {
        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        Employee[] staff = new Employee[3];
        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
        }

        Manager[] managers = new Manager[10];
        staff = managers;
        //staff[0] = new Employee("Harry Hacker", 10000, 1990, 1, 1);
        //managers[0].setBonus(1000);
    }
}
