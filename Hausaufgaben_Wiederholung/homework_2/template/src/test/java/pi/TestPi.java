package pi;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestPi {

    @Test
    public void testMonteCarloPi() {
        MonteCarloPi monteCarloPi = new MonteCarloPi();
        monteCarloPi.start();
        try {
            Thread.sleep(1000);
            monteCarloPi.interrupt();
            monteCarloPi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(Math.PI, monteCarloPi.getPi(), 0.1);
    }

    @Test
    public void testMonteCarloPiRunner() {
        MonteCarloPiRunner runner = new MonteCarloPiRunner(4, 1000);
        assertEquals(Math.PI, runner.getPi(), 0.1);
    }
}
