package CoreJavaVolumePartOne.chapter5.part2;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-7
 * @author: LiuDongMan
 * @createdDate: 2019-05-18 11:00
 **/
public class Student extends Person {
    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }

    @Override
    public String toString() {
        return super.toString() + "[major=" + major + "]";
    }
}
