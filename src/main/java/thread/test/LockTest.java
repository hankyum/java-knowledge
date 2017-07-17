package thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest extends Thread {
    private int threadNo;
    private Lock lock;

    public LockTest(int threadNo, Lock lock) {
        this.threadNo = threadNo;
        this.lock = lock;
    }

    public void run() {
        lock.lock();
        try {
            for (int i = 1; i < 10; i++) {
                System.out.println("Lock Test No." + threadNo + ":" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("你好");
        Lock lock = new ReentrantLock();
        for (int i = 1; i < 10; i++) {
            new LockTest(i, lock).start();
        }
    }
}
