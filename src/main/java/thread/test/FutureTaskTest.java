package thread.test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) {
       FutureTask<Integer> future = new FutureTask<>(() -> new Random().nextInt(100));

        new Thread(future).start();

        try {
//            Thread.sleep(1000);
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
