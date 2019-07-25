package CoreJavaVolumePartOne.chapter5.part2;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription: 程序清单5-5
 * @author: LiuDongMan
 * @createdDate: 2019-05-18 10:55
 **/
public abstract class Person {
    public abstract String getDescription();
    private String name;

    public Person(String name) {
        System.out.println(getClass().getName());
        this.name = name;
    }

    public String getName() {
        System.out.println("进来了");
        return name;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + "]";
    }
}
