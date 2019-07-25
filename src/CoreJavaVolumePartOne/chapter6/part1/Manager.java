package CoreJavaVolumePartOne.chapter6.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-05-20 21:37
 **/
public class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public int compareTo(Employee o) {
        Manager otherManager = (Manager) o;
        return 0;
    }
}
