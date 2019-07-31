package producerconsumerproblemwithcontainer;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {

	private Container queue;
	private int maxSize;

	public Producer(Container queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {
		Random random = new Random();
		while (!isInterrupted()) {
			int i = random.nextInt();
			System.out.println("Produzierter Wert : " + i);
			queue.add(i);
		}
	}
}
