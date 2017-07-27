package interview.morgan.kewill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Park {

    public int fee(String e, String l) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm");
        long parkMs = format.parse(l).getTime() - format.parse(e).getTime();
        long parkM = parkMs/(1000 * 60);
        System.out.println(parkM);
        long parkH = parkM / 60 + (parkM % 60 == 0 ? 0 : 1);
        return (int) (2 + (parkH > 1 ? (3 + (parkH - 1) * 4) : 3));
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new Park().fee("11:00", "13:30"));
        System.out.println(new Park().fee("23:00", "24:30"));
    }
}
