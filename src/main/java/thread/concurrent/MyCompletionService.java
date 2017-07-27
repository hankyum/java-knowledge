package thread.concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletionService {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<String> completion = new ExecutorCompletionService<String>(service);
        for (int i = 0; i < 10; i++) {
            final String data = i + "";
            completion.submit(() -> {
                Integer time = (int) (Math.random() * 1000);
                try {
                    System.out.println(data + " start");
                    Thread.sleep(time);
                    System.out.println(data + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return data + " callable ";
            });
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Take result => " + completion.take().get());
        }
        service.shutdown();
    }


}