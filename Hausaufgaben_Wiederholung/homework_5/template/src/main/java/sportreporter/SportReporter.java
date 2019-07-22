package sportreporter;

import java.util.concurrent.BlockingQueue;

/**
 * Represents a reporter posting comments of the current match to his
 * live ticker.
 * @author prog2-team
 *
 */
public class SportReporter implements Runnable {

	
	/**
	 * Sport reporter used for comment a match.
	 * @param match name of the match (e.g. names of the playing teams)
	 * @param eventList list for posting the comments
	 */
	public SportReporter(String match, BlockingQueue<String> eventList) {
	}
	
	@Override
	public void run() {
	}

}
