package thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore extends Thread {
	Semaphore position;
	private int id;

	public MySemaphore(int i, Semaphore s) {
		this.id = i;
		this.position = s;
	}

	public void run() {
		try {
			if (position.availablePermits() > 0) {
				System.out.println("有空闲 [" + this.id + "] ");
			} else {
				System.out.println("没有空闲[" + this.id + "] 等待");
			}
			position.acquire();
			System.out.println("使用 [" + this.id + "] 开始");
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println("使用 [" + this.id + "] 结束");
			position.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService list = Executors.newCachedThreadPool();
		Semaphore position = new Semaphore(5);
		for (int i = 0; i < 10; i++) {
			list.submit(new MySemaphore(i + 1, position));
		}
		list.shutdown();

		position.acquireUninterruptibly(5);
		System.out.println("程序结束");
		position.release(5);
	}
}