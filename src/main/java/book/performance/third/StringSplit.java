package book.performance.third;

import java.util.StringTokenizer;

/**
 * Created by hguo on 7/18/17.
 */
public class StringSplit {
    static final int COUNT = 10000;
    static final boolean PRINT = false;
    abstract static class PerformanceTest {
        void start() {
            long begin = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                test();
                if (PRINT) {
                    System.out.println();
                }
            }
            System.out.println((System.currentTimeMillis() - begin));
        }

        abstract void test();
    }

    public static void main(String[] args) {
        final String orgStr;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < COUNT; i++) {
            sb.append(i).append(";");
        }
        orgStr = sb.toString();
        System.out.println("test split");
        new PerformanceTest() {
            @Override
            void test() {
                for (String str: orgStr.split(";")) {
//                    System.out.print(PRINT ? str + " " : "");
                }
            }
        }.start();
        System.out.println("test StringTokenizer");
        new PerformanceTest() {
            StringTokenizer st = new StringTokenizer(orgStr, ";");
            @Override
            void test() {
                while (st.hasMoreElements()) {
                    String element = (String) st.nextElement();
//                    System.out.print(PRINT ? element + " " : "");
                }
                st = new StringTokenizer(orgStr, ";");
            }
        }.start();
        System.out.println("test own split");
        new PerformanceTest() {
            String tmp = orgStr;
            @Override
            void test() {
                while (true) {
                    int j = tmp.indexOf(";");
                    if (j < 0) break;
                    String splitStr = tmp.substring(0, j);
//                    System.out.print(PRINT ? splitStr + " " : "");
                    tmp = tmp.substring(j + 1);
                }
                tmp = orgStr;
            }
        }.start();
    }
}
