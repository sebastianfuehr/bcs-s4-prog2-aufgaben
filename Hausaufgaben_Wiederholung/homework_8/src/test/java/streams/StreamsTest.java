package streams;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamsTest {

    @Test
    public void testOrder() {
        Stream.of("d2", "a2", "b1", "b3", "c", "a6")
                .filter(s -> { System.out.println("filter: " + s); return s.startsWith("a");
                })
                .limit(2)
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    public void paralellVsSequentialStreamBenchmarkTest() {
        final int amountOfTests = 5;
        System.out.println("Test\t Sequential\t Paralell");
        int timeSeq = 0;
        int timePar = 0;
        for (int i=0; i<amountOfTests; i++) {
            long seq = testSequentialStream();
            long par = testParalellStream();
            timeSeq += seq;
            timePar += par;
            System.out.println("\t" + i + "\t\t\t" + seq + "\t\t\t" + par);
        }
        System.out.println("Avg.:\t\t\t" + (timeSeq/amountOfTests) + "\t\t\t" + (timePar/amountOfTests));
    }

    private long testSequentialStream() {
        long start = 0L;
        long end = 0L;
        try {
            List<Record> list = Citizens.citizenList();
            start = System.currentTimeMillis();
            long amount = list.stream().filter(r -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Process was interrupted.");
                    e.printStackTrace();
                }
                return (r.geschlecht.equals("2"));
            }).count();
            end = System.currentTimeMillis();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return end - start;
    }

    private long testParalellStream() {
        long start = 0L;
        long end = 0L;
        try {
            List<Record> list = Citizens.citizenList();
            start = System.currentTimeMillis();
            long amount = list.parallelStream().filter(r -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Process was interrupted.");
                    e.printStackTrace();
                }
                return (r.geschlecht.equals("2"));
            }).count();
            end = System.currentTimeMillis();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return end - start;
    }
}
