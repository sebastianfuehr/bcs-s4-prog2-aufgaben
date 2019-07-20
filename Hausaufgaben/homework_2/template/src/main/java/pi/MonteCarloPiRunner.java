package pi;

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
    }

    public double getPi() {
        MonteCarloPi[] threads = new MonteCarloPi[par];
        for (int i=0; i<par; i++) {
            threads[i] = new MonteCarloPi();
            threads[i].start();
        }
        try {
            Thread.sleep(millis);
            double piSum = 0.0;
            for (MonteCarloPi tr: threads) {
                tr.interrupt();
            }
            for (MonteCarloPi tr: threads) {
                tr.join(); //wichtig! Damit Werte fertig gerechnet werden.
                piSum += tr.getPi();
            }
            pi = piSum/par;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pi;
    }
}
