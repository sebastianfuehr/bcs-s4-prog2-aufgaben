package pi;

import java.util.Arrays;

/**
 * A Runner class, running multiple threads in parallel
 *
 * @author YOU
 */
public class MonteCarloPiRunner extends Thread {

    final int par;
    final long millis;
    private double pi = 1.0;

    /**
     * Initializes the runner with a given degree of parallelism and
     * a duration.
     *
     * @param par degree of parallelism (number of threads)
     * @param millis duration of computation
     */
    public MonteCarloPiRunner(int par, long millis) {
        this.par = par;
        this.millis = millis;

        MonteCarloPi[] threads = new MonteCarloPi[par];
        for (int i=0; i<par; i++) {
            threads[i] = new MonteCarloPi();
            threads[i].start();
        }
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0; i<par; i++) {
            threads[i].interrupt();
        }
        for (int i=0; i<par; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        pi = Arrays.stream(threads).map(t -> t.getPi()).reduce(Double::sum).get() / threads.length;
        System.out.println(String.format("Run %d threads to calculate a pi of %g", par, pi));
    }

    public double getPi() {
        return pi;
    }
}
