package mandelbrot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Computes a visualization of the Mandelbrot-Set.
 *
 * @author Prog2-Team
 */
public class Mandelbrot {

    /*
    #Threads    Time
    1           359 ms; 292 ms; 307 ms; 302 ms -> 315 ms
    2           172 ms; 200 ms; 187 ms; 191 ms -> 187,5 ms
    3           203 ms; 194 ms; 174 ms; 215 ms -> 196,5 ms
    4           178 ms; 140 ms; 160 ms; 160 ms -> 159,5 ms
    8           279 ms; 129 ms; 137 ms; 179 ms -> 181 ms
    16          141 ms; 198 ms; 165 ms; 207 ms -> 177,75 ms
    32          408 ms; 402 ms; 494 ms; 419 ms -> 430,75 ms
    64          485 ms; 330 ms; 330 ms; 385 ms -> 382,5 ms
     */

    // Image Dimensions
    private static final double SCALE = 1.0; // Crank me up (carefully)!
    private static final double RATIO = 1920.0 / 1080.0;
    private static final int HEIGHT = (int)(1080.0 * SCALE);
    private static final int WIDTH = (int)(HEIGHT * RATIO);

    // Convergence iterations
    private static final int MAX_ITERATIONS = 100;

    // The "Viewport"
    private static final double WEST = -1.95;
    private static final double EAST = 1.60;
    private static final double NORTH = -1.15;
    private static final double SOUTH = 1.10;

    // Colors
    private final static int BLACK = 0x00000000;
    private final static int[] colors = IntStream.range(0, MAX_ITERATIONS)
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
        /*
        ... main(String[] args) {
        par = Integer.parseInt(args[0]);
        ...
         */
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int par = Integer.parseInt(input); // #Threads
        System.out.println(String.format("Running on %d Thread/s", par));
        final int[] buf = new int[WIDTH * HEIGHT];
        Thread[] threads = new Thread[par];
        int nbrOfPixels = WIDTH * HEIGHT / par;
        int restOfPixels = (WIDTH * HEIGHT) % par;
        long start = System.currentTimeMillis();
        for (int i = 0; i < par; i++) {
            int pixelsToBeComputed = (i+1)*nbrOfPixels;
            if (i == threads.length-1) pixelsToBeComputed += restOfPixels;
            threads[i] = new Thread(new PixelComputingThread(i*nbrOfPixels, pixelsToBeComputed, buf));
            threads[i].start();
        }
        for (int i = 0; i < par; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                threads[i].interrupt();
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println(String.format("Took %d milliseconds", stop - start));
        try {
            ImageIO.write(render(buf),"png", new File("mandelbrot.png"));
        } catch (IOException e) {
            System.err.println(String.format("Unable to save image: %s", e.toString()));
        }
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getMaxIterations() {
        return MAX_ITERATIONS;
    }

    public static double getWEST() {
        return WEST;
    }

    public static double getEAST() {
        return EAST;
    }

    public static double getNORTH() {
        return NORTH;
    }

    public static double getSOUTH() {
        return SOUTH;
    }

    public static int getBLACK() {
        return BLACK;
    }

    public static int[] getColors() {
        return colors;
    }

}