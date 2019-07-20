package mandelbrot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Computes a visualization of the Mandelbrot-Set.
 *
 * @author Prog2-Team
 */
public class Mandelbrot {

    // Table for scale of "5.0"
    // Threads   Time
    //       1      4473 ms
    //       2      2378 ms
    //       3      2745 ms
    //       4      1930 ms
    //       8      1261 ms
    //      16       992 ms
    //      32       783 ms
    //      64       719 ms

    // Image Dimensions
    static final double SCALE = 1.0; // Crank me up (carefully)!
    static final double RATIO = 1920.0 / 1080.0;
    static final int HEIGHT = (int)(1080.0 * SCALE);
    static final int WIDTH = (int)(HEIGHT * RATIO);

    // Convergence iterations
    static final int MAX_ITERATIONS = 100;

    // The "Viewport"
    static final double WEST = -1.95;
    static final double EAST = 1.60;
    static final double NORTH = -1.15;
    static final double SOUTH = 1.10;

    // Colors
    final static int BLACK = 0x00000000;
    final static int[] colors = IntStream.range(0, MAX_ITERATIONS)
                    // Color Scheme courtesy of https://github.com/joni/fractals
                    .map(i -> Color.HSBtoRGB(i/256f, 1, i/(i+8f)))
                    .toArray();


    /**
     * Renders the image from the buffer to a buffered image
     *
     * @param buf the buffer
     * @return an image to be saved or displayed
     */
    private static BufferedImage render(int[]buf) {
        assert(buf.length == WIDTH * HEIGHT);
        final BufferedImage image =
                new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                image.setRGB(x, y, buf[y * WIDTH + x]);
            }
        }
        return image;
    }

    /**
     * Computes <pre>mandelbrot.png</pre> and writes it to disk.
     * 
     * @param args in the solution <pre>args[0]</pre> should contain the number of threads
     */
    public static void main(String[] args) {
        int par = Integer.parseInt(args[0]);
        System.out.println(String.format("Running on %d Thread/s", par));
        final int[] buf = new int[WIDTH * HEIGHT];
        long start = System.currentTimeMillis();

        int calcWidth = WIDTH*HEIGHT / par;
        Thread[] threads = new Thread[par];
        for (int i=0; i<par; i++) {
            threads[i] = new Thread(new Calculator(i*calcWidth, (i+1)*calcWidth, buf));
            threads[i].start();
        }
        for (int i=0; i<par; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(String.format("Thread %d was interrupted unexpected.", i));
            }
        }

        long stop = System.currentTimeMillis();
        System.out.println(String.format("Took %d milliseconds", stop - start));
        try {
            ImageIO.write(render(buf),"png", new File("mandelbrot.png"));
            System.out.println("Written mandelbrot.png");
        } catch (IOException e) {
            System.err.println(String.format("Unable to save image: %s", e.toString()));
        }
    }
}