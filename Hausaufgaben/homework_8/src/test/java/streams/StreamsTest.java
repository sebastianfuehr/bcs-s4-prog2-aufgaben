package streams;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsTest {

    // Original mit mehr Elementen: 14
    // Reihenfolge der Ausführungsbefehle: 10
    // Sortieren: 20 an erster Stelle, später sogar gar keinen Sinn mehr...
    /*
    .sorted((e1, e2) -> {
                    System.out.println("sort: " + e1);
                    return Character.compare(e1.charAt(0), e2.charAt(0));})
     */
    @Test
    public void testOrder() {
        Stream.of("d2", "a2", "b1", "b3", "c", "a6", "a5", "b", "c4")
                .filter(s -> { System.out.println("filter: " + s); return s.startsWith("a");
                })
                .limit(2)
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    public void testSequentialVsParallelStream() {
        int benchmarkSize = 3;

        // sequential stream test
        int actualBenchmarkSize = benchmarkSize;
        long res = 0;
        for (int i=0; i < benchmarkSize; i++) {
            try {
                res += testSequentialStream();
                System.out.println("Test "+i+" successful.");
            } catch(UnsupportedEncodingException | FileNotFoundException | WrongTestResultException e) {
                System.out.println("Test "+i+" failed.");
                actualBenchmarkSize = actualBenchmarkSize - 1;
            }
        }
        printTestResults(false, res, benchmarkSize, actualBenchmarkSize);

        // parallel stream test
        actualBenchmarkSize = benchmarkSize;
        res = 0;
        for (int i=0; i < benchmarkSize; i++) {
            try {
                res += testParallelStream();
                System.out.println("Test "+i+" successful.");
            } catch(UnsupportedEncodingException | FileNotFoundException | WrongTestResultException e) {
                System.out.println("Test "+i+" failed.");
                actualBenchmarkSize = actualBenchmarkSize - 1;
            }
        }
        printTestResults(true, res, benchmarkSize, actualBenchmarkSize);
    }

    /**
     * Should count 3564 females.
     * @return milliseconds Time the sequential method needed to filter through the records.
     */
    private long testSequentialStream() throws UnsupportedEncodingException, FileNotFoundException, WrongTestResultException {
        List<Record> list = Citizens.citizenList();
        long start = System.currentTimeMillis();
        long amount = list.stream() // einziger Unterschied zu testParallelStream()
            .filter(record -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    if (!Thread.interrupted()) Thread.currentThread().interrupt();
                }
                return record.geschlecht.equals("2");
            })
            .count();
        long end = System.currentTimeMillis();
        if (amount != 3564) throw new WrongTestResultException("Test result was not 3564");
        return end - start;
    }

    /**
     * Should count 3564 females.
     * @return milliseconds Time the parallel method needed to filter through the records.
     */
    private long testParallelStream() throws UnsupportedEncodingException, FileNotFoundException, WrongTestResultException {
        List<Record> list = Citizens.citizenList();
        long start = System.currentTimeMillis();
        long amount = list.parallelStream() // einziger Unterschied zu testSequentialStream()
                .filter(record -> {
                    try {
                        Thread.sleep(1);
                    } catch(InterruptedException e) {
                        if (!Thread.interrupted()) Thread.currentThread().interrupt();
                    }
                    return record.geschlecht.equals("2");
                })
                .count();
        long end = System.currentTimeMillis();
        if (amount != 3564) throw new WrongTestResultException("Test result was not 3564");
        return end - start;
    }

    /**
     * Prints the test results after a specific number of tests was run.
     * @param parallel Was the test done with a parallel or a sequential stream?
     * @param result The average length of all filter tests.
     * @param benchmarkSize The benchmark size.
     * @param actualBenchmarkSize The benchmark size which was actually used (can be corrupted by wrong test
     *                            results or FileNotFoundExceptions or UnsupportedEncodingExceptions.
     */
    private void printTestResults(boolean parallel, long result, int benchmarkSize, int actualBenchmarkSize) {
        String parallelString = "sequential";
        if (parallel) parallelString = "parallel";
        System.out.println(
                "---------------------------------------------------------------\n"
                +"Results of benchmark test for "+parallelString+" stream: \n"
                +"Average filter duration: "+(result/actualBenchmarkSize)+"ms\n"
                +"Expected benchmark size: "+benchmarkSize+"\n"
                +"Actual benchmark size: "+actualBenchmarkSize+"\n"
                +"--------------------------------------------------------------"
        );
    }

}
