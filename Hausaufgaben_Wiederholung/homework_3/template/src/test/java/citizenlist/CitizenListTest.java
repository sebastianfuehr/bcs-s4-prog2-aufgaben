package citizenlist;

import citizens.Citizens;
import citizens.Record;
import org.junit.Test;
import predicates.GermanMenBetween25And35FromCharlottenburg;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;

public class CitizenListTest {
    @Test
    public void testCitizenFilter() throws IOException {
        List<Record> citizens = Citizens.load();
        CitizenList records = new CitizenList(citizens);
        GermanMenBetween25And35FromCharlottenburg predicate = new GermanMenBetween25And35FromCharlottenburg();
        CitizenList newList = records.filter(predicate);
        System.out.println(newList.size());
        assert(newList.size() == 2);
    }
}
