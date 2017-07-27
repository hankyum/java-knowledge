package interview.morgan;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hguo on 7/10/
 */
public class LinkedListTest {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<String>();
        q.add("Veronica");
        q.add("Wallace");
        q.add("Duncan");
        showAll(q);
    }

    public static void showAll(Queue q) {
        q.add(new Integer(42));
        while (!q.isEmpty())
            System.out.print(q.remove() + " ");
    }
}
