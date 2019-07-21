package database;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class TableTest {
    /**
     * Tests {@link Table#reduce(Table.Aggregation)} using the sum function.
     */
    @Test
    public void testReduce() {
        Table<Integer> integers = new Table<>(Arrays.asList(1, 2, 3, 4));
        int sum = integers.reduce(new Table.Aggregation<Integer, Integer>() {
            @Override
            public Integer initial() {
                return 0;
            }

            @Override
            public Integer apply(Integer accumulator, Integer next) {
                return accumulator + next;
            }
        });
        Assert.assertEquals(1 + 2 + 3 + 4, sum);
    }

    /**
     * Find the longest headsign in trips.
     *
     * @throws IOException
     */
    @Test
    public void testFindLongestHeadsign() throws IOException {
        String longestHeadsign = Tables.findLongestHeadsign();
        Assert.assertNotNull(longestHeadsign);
        Assert.assertEquals(52, longestHeadsign.length());
        System.out.println(longestHeadsign);
    }
}
