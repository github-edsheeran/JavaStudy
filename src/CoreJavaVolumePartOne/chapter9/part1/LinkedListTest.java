package CoreJavaVolumePartOne.chapter9.part1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @program: CoreJavaVolumePartOne
 * @description: This program demonstrates operation on linked lists.
 * @chineseDescription: 程序清单9-1
 * @author: LiuDongMan
 * @createdDate: 2019-06-22 20:57
 **/
public class LinkedListTest {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doung");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }

            aIter.add(bIter.next());
        }

        System.out.println(a);

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();

            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);

        a.removeAll(b);

        System.out.println(a);
    }
}
