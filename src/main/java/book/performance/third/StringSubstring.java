package book.performance.third;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hguo on 7/17/17.
 */
public class StringSubstring {
    public static void main(String[] args) {
        List<String> handler = new ArrayList<>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            HugeStr h =  new HugeStr();
            handler.add(h.getSubstring(1, 5));
        }
        System.out.println(HugeStr.class.getName() + " time " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ImprovedHugeStr h =  new ImprovedHugeStr();
            handler.add(h.getSubstring(1, 5));
        }
        System.out.println(ImprovedHugeStr.class.getName() + " time " + (System.currentTimeMillis() - begin));

    }

    static class HugeStr {
        private String str = new String(new char[10000000]);

        public String getSubstring(int begin, int end) {
            return str.substring(begin, end);
        }
    }

    static class ImprovedHugeStr {
        private String str = new String(new char[10000000]);

        public String getSubstring(int begin, int end) {
            return new String(str.substring(begin, end));
        }
    }
}


