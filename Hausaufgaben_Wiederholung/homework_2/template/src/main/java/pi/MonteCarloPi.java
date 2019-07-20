package pi;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A Thread approximating the number π. The Thread should be able to handle interruption.
 *
 */
public class MonteCarloPi extends Thread {

    /**
     * Should contain the approximated value, once the thread terminates.
     */
    private double pi = 1.0;

    @Override
    public void run() {
        final double R = 1.0;
        int hits = 0, tries = 0;
        ThreadLocalRandom current = ThreadLocalRandom.current();
        while (!isInterrupted()) {
            double x = current.nextDouble(R), y = current.nextDouble(R);
            if (x * x + y * y < R * R) {
                hits += 1;
            }
            tries += 1;
            pi = 4.0 * (double) hits / (double) tries;
        }
    }

    /**
     * Returns the approximation of the number π.
     *
     * @return the approximated number π
     */
    public double getPi() {
        return pi;
    }
}
