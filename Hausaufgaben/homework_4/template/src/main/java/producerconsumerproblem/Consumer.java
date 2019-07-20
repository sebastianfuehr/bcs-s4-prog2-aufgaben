package producerconsumerproblem;

import java.util.Queue;

public class Consumer extends Thread {
	private Queue<Integer> queue;
	private int maxSize;

	public Consumer(Queue<Integer> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					System.out
							.println("Queue ist leer, "
									+ "Consumer wartet auf Hinzufügen durch den Producer");
					try {
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("Gelesener Wert : " + queue.remove());
				queue.notifyAll();
			}
		}
	}
}
