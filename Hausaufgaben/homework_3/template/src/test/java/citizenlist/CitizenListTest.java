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
        List<Record> citizens = Citizens.load();
        CitizenList records = new CitizenList(citizens);
        CitizenList newList = records.filter(new GermanMan_25_35());
        assert(newList.size() == 2);
    }
}
