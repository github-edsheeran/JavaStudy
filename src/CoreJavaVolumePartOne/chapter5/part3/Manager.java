package CoreJavaVolumePartOne.chapter5.part3;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-10
 * @author: LiuDongMan
 * @createdDate: 2019-05-19 19:42
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
        return baseSalary + bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        Manager other = (Manager) obj;
        return bonus == other.bonus;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 17 * new Double(bonus).hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}
