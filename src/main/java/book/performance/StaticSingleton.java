package book.performance;

/**
 * Created by hguo on 7/14/17.
 */
public class StaticSingleton {
    private StaticSingleton() {
        System.out.println("Static Singleton is created.");
    }

    private static StaticSingleton lazy;

    private static synchronized StaticSingleton getLazy() {
        if (lazy == null) {
            lazy = new StaticSingleton();
        }
        return lazy;
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(getLazy());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
