package pi;

import org.junit.Test;

import java.util.Random;

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(Math.PI, monteCarloPi.getPi(), 0.1);
    }

    @Test
    public void testMonteCarloPiRunner() {
        Random rand = new Random();
        MonteCarloPiRunner runner = new MonteCarloPiRunner(rand.nextInt(128), 1000);
        assertEquals(Math.PI, runner.getPi(), 0.1);
    }
}
