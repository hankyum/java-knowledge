package thread.concurrent;

import java.util.concurrent.*;

public class MyBlockingQueue extends Thread {

    public static void main(String args[]) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final String data = i + " value in queue";
            service.execute(() -> {
                try {
                    queue.put(data);
                    System.out.println("put  -> " + data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        service.execute(() -> {
            try {
                while (true) {
                    countDownLatch.await();
                    Thread.sleep((int) (Math.random() * 1000));
                    if (queue.isEmpty()) {
                        break;
                    }
                    System.out.println("take -> " + queue.take());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        countDownLatch.countDown();
        service.shutdown();
    }
}
