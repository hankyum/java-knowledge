package thread;

public class MyThreadLocal {

    private ThreadLocal<Integer> threadLocalCount = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return 0;
        }

    };

    public void test() {
        for (int i = 0; i < 3; i++) {
            new Thread(new CounterRunnable()).start();
        }
    }

    class CounterRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread name " + Thread.currentThread().getName() + " Init value: " + threadLocalCount.get());
            threadLocalCount.set(threadLocalCount.get() + 10);
            System.out.println("Thread name " + Thread.currentThread().getName() + " Changed value: " + threadLocalCount.get());
        }

    }

    public static void main(String[] args) {
        new MyThreadLocal().test();
    }

}
