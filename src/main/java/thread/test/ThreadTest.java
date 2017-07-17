package thread.test;

public class ThreadTest extends Thread {
    private int threadNo;

    public ThreadTest(int threadNo) {
        this.threadNo = threadNo;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            new ThreadTest(i).start();
        }
    }

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            System.out.println("No." + threadNo + ":" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
