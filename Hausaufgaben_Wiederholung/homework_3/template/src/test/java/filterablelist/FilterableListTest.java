package filterablelist;

import citizens.Citizens;
import citizens.Record;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;

public class FilterableListTest {
    @Test
    public void testFilter() throws IOException {
        List<Record> citizens = Citizens.load(); // TODO exchange with your implementation (or keep this)
        FilterableList<Record> records = new FilterableList<>(citizens);
        // FilterableList<Record> newList = records.filter(YOUR_FILTER_GOES_HERE);
        // assert(newList.size() == ??);  TODO check results
        fail("Not implemented"); // TODO remove this line!
    }
}
