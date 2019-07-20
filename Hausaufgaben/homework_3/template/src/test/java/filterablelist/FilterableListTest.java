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
        List<Record> citizens = Citizens.load();
        FilterableList<Record> records = new FilterableList<>(citizens);
        FilterableList<Record> newList = records.filter(new ListPredicate<Record>() {
            @Override
            public boolean test(Record record) {
                if (record.geschlecht.equals(Citizens.SEX_M)
                        && record.staatsangehörigkeit.equals("D")
                        && record.minAlter >= 25
                        && record.maxAlter <= 35
                        && record.ortsteilName.equals("Blankenfelde")) return true;
                return false;
            }
        });
        assert(newList.size() == 2);
    }
}
