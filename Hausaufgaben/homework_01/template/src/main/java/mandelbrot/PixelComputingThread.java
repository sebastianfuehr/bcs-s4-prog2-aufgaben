package mandelbrot;

public class PixelComputingThread implements Runnable {

    private int from,
                until;
    private int[] buf;

    public PixelComputingThread(int from, int until, int[] buf) {
        this.from = from;
        this.until = until;
        this.buf = buf;
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
        while(x * x + y * y < 2 && i < Mandelbrot.getMaxIterations()) {
            double xt = x * x - y * y + xc;
            double yt = 2 * x * y + yc;
            x = xt;
            y = yt;
            i++;
        }
        return i;
    }

    /**
     * Computes a slice of the mandelbrot set and writes it to an int[] buffer.
     *
     * @param from beginning of slice (0 or above)
     * @param until end of slice (exclusive)
     * @param buf the buffer to be filled
     */
    @Override
    public void run() {
        assert(from >= 0);
        assert(until < buf.length);
        for (int i = from; i < until; i++) {
            int x = i % Mandelbrot.getWIDTH();
            int y = i / Mandelbrot.getWIDTH();
            double xc = Mandelbrot.getWEST() + (Mandelbrot.getEAST() - Mandelbrot.getWEST()) * x / Mandelbrot.getWIDTH();
            double yc = Mandelbrot.getNORTH() + (Mandelbrot.getSOUTH() - Mandelbrot.getNORTH()) * y / Mandelbrot.getHEIGHT();
            int iterations = pixel(xc, yc);
            buf[i] = (iterations < Mandelbrot.getMaxIterations()) ? Mandelbrot.getColors()[iterations] : Mandelbrot.getBLACK();
        }
    }

}
