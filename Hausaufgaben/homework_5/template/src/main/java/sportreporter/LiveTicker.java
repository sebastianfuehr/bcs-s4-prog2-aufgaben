package sportreporter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a server consuming comments of
 * sport reporters, updating the output.
 * @author prog2-team
 *
 */
public class LiveTicker implements Runnable {

	// add() and take() are not synchronized to one another
	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(1000);
				String currString = queue.take();
				System.out.println(currString);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return returns the list containing the comments
	 */
	public BlockingQueue<String> getEventList() {
		return queue;
	}

	public static void main(String[] args){
		LiveTicker liveTicker = new LiveTicker();
		SportReporter reporter1 = new SportReporter("München vs. Dortmund", liveTicker.getEventList());
		SportReporter reporter2 = new SportReporter("Berlin vs. Hamburg", liveTicker.getEventList());
		SportReporter reporter3 = new SportReporter("Stuttgart vs. Köln", liveTicker.getEventList());

		Thread[] thr = new Thread[3];
		thr[0] = new Thread(reporter1);
		thr[1] = new Thread(reporter2);
		thr[2] = new Thread(reporter3);
		for (Thread curr: thr) {
			curr.start();
		}
		Thread ticker = new Thread(liveTicker);
		ticker.start();
		System.out.println("All sport reporters and the live ticker set in action.");
	}
	
}
