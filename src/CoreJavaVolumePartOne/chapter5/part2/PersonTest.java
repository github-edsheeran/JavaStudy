package CoreJavaVolumePartOne.chapter5.part2;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates abstract classes.
 * @chineseDescription: 程序清单5-4
 * @author: LiuDongMan
 * @createdDate: 2019-05-18 10:55
 **/
public class PersonTest {
    public static void main(String[] args) {
        Person[] people = new Person[2];

        people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        people[1] = new Student("Maria Morris", "computer science");

//        for (Person p : people) {
//            System.out.println(p.getName() + ", " + p.getDescription());
//        }

        System.out.println(people[0].toString() + "-" + people[0].getName());
        System.out.println(people[1].toString() + "-" + people[1].getName());
    }
}
