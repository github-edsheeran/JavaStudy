package CoreJavaVolumePartOne.chapter9;

import java.util.*;

/**
 * @program: CoreJavaVolumePartOne
 * @description:
 * @chineseDescription:
 * @author: LiuDongMan
 * @createdDate: 2019-06-21 14:18
 **/
public class Test {
    public static void main(String[] args) {
//        hashSetTest();
        hashCodeAndEquals();
    }

    static void hashSetTest() {
        Set<String> hashSet = new HashSet<>();
        System.out.println("HashSet初始容量大小: " + hashSet.size());

        hashSet.add("my");
        hashSet.add("name");
        hashSet.add("is");
        hashSet.add("liudongman");
        hashSet.add(",");
        hashSet.add("hello");
        hashSet.add("world");
        hashSet.add("!");

        System.out.println("HashSet容量大小: " + hashSet.size());

        Iterator<String> iterator = hashSet.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
        }

        for (String str : hashSet) {
            if ("liudongman".equals(str)) {
                System.out.println("你就是我想要的元素: " + str);
            }

            System.out.println(str);
        }

        hashSet.remove("liudongman");
        System.out.println("HashSet容量大小: " + hashSet.size());
        hashSet.clear();
        System.out.println("HashSet容量大小: " + hashSet.size());

        boolean isEmpty = hashSet.isEmpty();
        System.out.println("HashSet是否为空: " + isEmpty);
        boolean isContains = hashSet.contains("hello");
        System.out.println("HashSet是否包含hello字符串: " + isContains);
    }

    static void hashCodeAndEquals() {
        // 第一个Set集合
        Set<String> set1 = new HashSet<>();
        String str1 = new String("liudongman");
        String str2 = new String("liudongman");
        set1.add(str1);
        set1.add(str2);
        System.out.println("长度: " + set1.size() + ", 内容为: " + set1);

        // 第二个Set集合
        Set<App> set2 = new HashSet<>();
        App app1 = new App();
        app1.setName("liudongman");

        App app2 = new App();
        app2.setName("liudongman");

        set2.add(app1);
        set2.add(app2);
        System.out.println("长度: " + set2.size() + ", 内容为: " + set2);

        //第三个Set集合
        Set<App> set3 = new HashSet<>();
        App app3 = new App();
        app3.setName("liudongman");
        set3.add(app3);
        set3.add(app3);
        System.out.println("长度: " + set3.size() + ", 内容为: " + set3);
    }
}

class App {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
