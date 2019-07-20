package sportreporter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a reporter posting comments of the current match to his
 * live ticker.
 * @author prog2-team
 *
 */
public class SportReporter implements Runnable {

	private LinkedBlockingQueue<String> repQueueRef;
	private String match;

	/**
	 * Sport reporter used for comment a match.
	 * @param match name of the match (e.g. names of the playing teams)
	 * @param eventList list for posting the comments
	 */
	public SportReporter(String match, BlockingQueue<String> eventList) {
		this.repQueueRef = (LinkedBlockingQueue<String>) eventList;
		this.match = match;
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Random random = new Random();
			long sec = (long) (random.nextDouble() * 6000);
			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repQueueRef.add(match + ": " + getRandomSoccerEvent());
		}
	}

	public String getRandomSoccerEvent() {
		Random random = new Random();
		int soccerEventNbr = random.nextInt(SoccerEvent.class.getEnumConstants().length);
		return SoccerEvent.class.getEnumConstants()[soccerEventNbr].getCatchword();
	}

}
