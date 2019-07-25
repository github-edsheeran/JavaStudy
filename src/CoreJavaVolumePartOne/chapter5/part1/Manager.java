package CoreJavaVolumePartOne.chapter5.part1;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-3
 * @author: LiuDongMan
 * @createdDate: 2019-05-16 17:15
 **/
public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary;
    }

    public void setBonus(double b) {
        bonus = b;
    }

    @Override
    public Manager test() {
        return null;
    }
}
