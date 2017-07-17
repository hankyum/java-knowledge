package thread.concurrent;

import java.util.concurrent.*;

public class MyBlockingQueue extends Thread {
    public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);
    private int index;

    public MyBlockingQueue(int i) {
        this.index = i;
    }

    public void run() {
        try {
            queue.put(String.valueOf(this.index) + " value in queue.");
            System.out.println("{" + this.index + "} in queue!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
//        MyBlockingQueue.putFirstTest();
        MyBlockingQueue.putAfterTest();
    }

    public static void putFirstTest() {
        System.out.println("Put First test");
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new MyBlockingQueue(i));
        }
        service.submit(takeQueue());
        service.shutdown();
    }

    public static void putAfterTest() {
        System.out.println("Put after test");
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch begin = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            service.submit(new MyBlockingQueue(i));
        }
        begin.countDown();
        service.submit(takeQueue());
        service.shutdown();
    }

    public static Thread takeQueue() {
        return new Thread() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep((int) (Math.random() * 1000));
                        if (MyBlockingQueue.queue.isEmpty()) {
                            break;
                        }
                        System.out.println(MyBlockingQueue.queue.take() + " has been take!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}