package interview.morgan;

/**
 * Created by hguo on 7/10/17.
 */
public class ClassTest {
    public static void main(String args[]) {
        X x1 = new X();
        X x2 = new Y();
        Y y1 = new Y();
        x1.do1();
        x2.do1();
        y1.do2();
        y1.do1();
        ((Y)x2).do2();
    }

}

class X {
    void do1() {

    }
}

class Y extends X {
    void do2() {

    }
}