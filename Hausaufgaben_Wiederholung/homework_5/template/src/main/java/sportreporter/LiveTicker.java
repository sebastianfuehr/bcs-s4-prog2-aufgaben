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

    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                System.out.println(queue.take());
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
        final int reporter = 3;

        LiveTicker liveTicker = new LiveTicker();
        SportReporter reporter1 = new SportReporter("München vs. Dortmund", liveTicker.getEventList());
        SportReporter reporter2 = new SportReporter("Berlin vs. Hamburg", liveTicker.getEventList());
        SportReporter reporter3 = new SportReporter("Stuttgart vs. Köln", liveTicker.getEventList());
        Thread[] threads = new Thread[reporter+1];
        threads[0] = new Thread(liveTicker);
        threads[1] = new Thread(reporter1);
        threads[2] = new Thread(reporter2);
        threads[3] = new Thread(reporter3);
        for (int i=0; i<reporter+1; i++) {
            threads[i].start();
        }
    }

}
