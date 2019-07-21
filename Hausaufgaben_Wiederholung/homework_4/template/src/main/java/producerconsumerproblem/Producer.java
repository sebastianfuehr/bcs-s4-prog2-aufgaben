package producerconsumerproblem;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
	private Queue<Integer> queue;
	private int maxSize;

	public Producer(Queue<Integer> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {
		Random random = new Random();
		while (true) {
			synchronized (queue) {
				while (queue.size() == maxSize) {
					try {
						System.out
								.println("Queue ist voll, "
										+ "Producer wartet auf Entnahme durch Consumer ");
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				int i = random.nextInt();
				System.out.println("Produzierter Wert : " + i);
				queue.add(i);
				queue.notifyAll();
			}
		}
	}
}
