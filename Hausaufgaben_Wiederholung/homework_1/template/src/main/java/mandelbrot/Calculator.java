package mandelbrot;

public class Calculator implements Runnable {

    private int from, until;
    private int[] buf;

    public Calculator(int from, int until, int[] buf) {
        this.from = from;
        this.until = until;
        this.buf = buf;
    }

    @Override
    public void run() {
        mandelbrot(from, until, buf);
    }

    /**
     * Computes a slice of the mandelbrot set and writes it to an int[] buffer.
     *
     * @param from beginning of slice (0 or above)
     * @param until end of slice (exclusive)
     * @param buf the buffer to be filled
     */
    private static void mandelbrot(int from, int until, int[]buf) {
        assert(from >= 0);
        assert(until < buf.length);
        for (int i = from; i < until; i++) {
            int x = i % Mandelbrot.WIDTH;
            int y = i / Mandelbrot.WIDTH;
            double xc = Mandelbrot.WEST + (Mandelbrot.EAST - Mandelbrot.WEST) * x / Mandelbrot.WIDTH;
            double yc = Mandelbrot.NORTH + (Mandelbrot.SOUTH - Mandelbrot.NORTH) * y / Mandelbrot.HEIGHT;
            int iterations = pixel(xc, yc);
            buf[i] = (iterations < Mandelbrot.MAX_ITERATIONS) ? Mandelbrot.colors[iterations] : Mandelbrot.BLACK;
        }
    }

    /**
     * computes a single pixel from the set
     *
     * @param xc x
     * @param yc y
     * @return number of iterations
     */
    private static int pixel(double xc, double yc) {
        int i = 0;
        double x = 0f, y = 0f;
        while(x * x + y * y < 2 && i < Mandelbrot.MAX_ITERATIONS) {
            double xt = x * x - y * y + xc;
            double yt = 2 * x * y + yc;
            x = xt;
            y = yt;
            i++;
        }
        return i;
    }

}
