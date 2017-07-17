package thread.concurrent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestScheduledThread {
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(() -> {
            System.out.println(new Date() + " run after 1 second, and repeat each 2 seconds");
        }, 1, 2, SECONDS);
        final ScheduledFuture beeperHandle2 = scheduler.scheduleWithFixedDelay(() -> {
            System.out.println(new Date() + " run after 2 second，and repeat after last task complete 5 seconds");
        }, 2, 5, SECONDS);
        scheduler.schedule(() -> {
            System.out.println(" shutdown after 30 seconds.");
            beeperHandle.cancel(true);
            beeperHandle2.cancel(true);
            scheduler.shutdown();
        }, 30, SECONDS);
    }
}