package SXT._3CollectionAndDataStructure.part1;

import java.util.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 实现一个简单的HashSet
 * @author: LiuDongMan
 * @createdDate: 2019-08-15 15:04
 **/
public class MyHashSet<E> {
    /**
     * HashSet底层是HashMap，因此HashSet内部的元素是无序的；TreeSet底层是TreeMap，因此TreeSet内部的元素是有序的
     */
    private HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    public MyHashSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    public void add(E element) {
        map.put(element, PRESENT);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
