package database;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class TableTest {

    /**
     * This is the example from the assignment sheet.
     */
    @Test
    public void testParReduce() {
        List<Integer> oneToThousand = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
        Table<Integer> table = new Table<>(oneToThousand);
        Integer integer = table.reduce(0, Integer::sum, Integer::sum);
        Assert.assertEquals(500500, (int)integer);
    }

    @Test
    public void testParMap() {
        List<Integer> thousand = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
        Table<Integer> integers = new Table<>(thousand);
        Table<Integer> mapped = integers.map(i -> i + 1);
        int[] expected = IntStream.rangeClosed(2, 1001).toArray();
        int[] actual = StreamSupport.stream(mapped.spliterator(), false).mapToInt(i -> i).toArray();
        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testParFilter() {
        List<Integer> ints = IntStream.range(0, 10000000).boxed().collect(Collectors.toList());
        Table<Integer> table = new Table<>(ints);
        Table<Integer> filtered = table.filter(i -> i % 2 == 0);
        Assert.assertEquals(5000000, filtered.size());
    }
}
