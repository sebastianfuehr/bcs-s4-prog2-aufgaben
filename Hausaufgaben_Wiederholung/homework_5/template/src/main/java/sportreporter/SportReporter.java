package sportreporter;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Represents a reporter posting comments of the current match to his
 * live ticker.
 * @author prog2-team
 *
 */
public class SportReporter implements Runnable {

	private BlockingQueue<String> eventList;
	private String match;
	
	/**
	 * Sport reporter used for comment a match.
	 * @param match name of the match (e.g. names of the playing teams)
	 * @param eventList list for posting the comments
	 */
	public SportReporter(String match, BlockingQueue<String> eventList) {
		this.match = match;
		this.eventList = eventList;
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			Random ran = new Random();
			try {
				Thread.sleep(ran.nextInt(6000));
				SoccerEvent[] arr = SoccerEvent.values();
				eventList.put(match + ": " + arr[ran.nextInt(arr.length)].toString()); // put() wartet, wenn Liste voll ist
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
