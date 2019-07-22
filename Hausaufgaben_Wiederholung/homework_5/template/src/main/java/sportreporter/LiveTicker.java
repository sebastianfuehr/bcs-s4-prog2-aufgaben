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

	@Override
	public void run() {
	}
	
	/**
	 * @return returns the list containing the comments
	 */
	public BlockingQueue<String> getEventList() {
		// TODO
		return null;
	}

	public static void main(String[] args){
		LiveTicker liveTicker = new LiveTicker();
		SportReporter reporter1 = new SportReporter("München vs. Dortmund", liveTicker.getEventList());
		SportReporter reporter2 = new SportReporter("Berlin vs. Hamburg", liveTicker.getEventList());
		SportReporter reporter3 = new SportReporter("Stuttgart vs. Köln", liveTicker.getEventList());
	}
	
}
