package citizenlist;

import citizens.Citizens;
import citizens.Record;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;

public class CitizenListTest {
    @Test
    public void testCitizenFilter() throws IOException {
        List<Record> citizens = Citizens.load(); // TODO exchange with your implementation (or keep this)
        CitizenList records = new CitizenList(citizens);
        // CitizenList newList = records.filter(YOUR_FILTER_GOES_HERE);
        // assert(newList.size() == ??);  TODO check results
        fail("Not implemented"); // TODO remove this line!
    }
}
