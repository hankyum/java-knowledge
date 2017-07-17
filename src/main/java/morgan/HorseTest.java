package morgan;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class HorseTest {
    public static void main(String[] args) {
        class Horse {
            public String name;

            public Horse(String s) {
                name = s;
            }
        }
        Object obj = new Horse("Zippo");
//        System.out.println(obj.name);

        Set set = new TreeSet();
        set.add("2");
        set.add("3");
        set.add("1");
        Iterator it = set.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
    }
}
