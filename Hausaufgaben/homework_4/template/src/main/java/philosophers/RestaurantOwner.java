package philosophers;

import static java.lang.Thread.currentThread;

public class RestaurantOwner implements Runnable {
    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
