package morgan;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet set = new TreeSet() {
            {
                add("six");
                add("one");
                add("two");
                add("three");
                add("four");
                add("one");
                add("one");
            }
        };
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next() + " ");
        }
    }
}
