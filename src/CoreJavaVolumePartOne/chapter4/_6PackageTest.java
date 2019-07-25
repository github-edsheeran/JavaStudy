package CoreJavaVolumePartOne.chapter4;
import CoreJavaVolumePartOne.com.horstmann.corejava._7Employee;

import static java.lang.System.*;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates the use of packages.
 * @chineseDescription: 程序清单4-6，4-7在com.horstmann.corejava中
 * @author: LiuDongMan
 * @createdDate: 2019-05-14 12:25
 **/
public class _6PackageTest {
    public static void main(String[] args) {
        // Because of the import statement, we don't have to use com.horstmann.core.Employee here
        _7Employee harry = new _7Employee("Harry Hacker", 50000, 1989, 10, 1);
        harry.raiseSalary(5);

        // Because of the static import statement, we don't have to use System.out here
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
    }
}
