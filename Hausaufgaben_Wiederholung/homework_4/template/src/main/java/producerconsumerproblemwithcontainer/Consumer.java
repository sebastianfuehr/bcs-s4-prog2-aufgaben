package producerconsumerproblemwithcontainer;

import java.util.Queue;

public class Consumer extends Thread {

	private Container queue;
	private int maxSize;

	public Consumer(Container queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	public void run() {
		while (!isInterrupted()) {
			System.out.println("Gelesener Wert : " + queue.remove());
		}
	}
}
