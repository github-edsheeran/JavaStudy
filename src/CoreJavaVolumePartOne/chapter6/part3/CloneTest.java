package CoreJavaVolumePartOne.chapter6.part3;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates cloning.
 * @chineseDescription: 程序清单6-4
 * @author: LiuDongMan
 * @createdDate: 2019-05-25 15:27
 **/
public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee original = new Employee("John Q. Public", 50000);
            original.setHireDay(2000, 1, 1);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 12, 31);
            System.out.println("original=" + original);
            System.out.println("copy=" + copy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
