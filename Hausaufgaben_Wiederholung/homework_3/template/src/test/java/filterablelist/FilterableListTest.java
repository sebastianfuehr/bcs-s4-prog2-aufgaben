package filterablelist;

import citizens.Citizens;
import citizens.Record;
import filterablelist.predicates.ListPredicate;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.fail;

public class FilterableListTest {
    @Test
    public void testFilter() throws IOException {
        List<Record> citizens = Citizens.load();
        FilterableList<Record> records = new FilterableList<>(citizens);
        // ListPredicateForRecords<Record> predicateInstance = new ListPredicateForRecords<Record>();
        FilterableList<Record> newList = records.filter(new ListPredicate<Record>() {
            @Override
            public boolean test(Record record) {
                return record.geschlecht.equals(Citizens.SEX_M)
                        && record.staatsangehörigkeit.equals("D")
                        && record.ortsteilName.equals("Charlottenburg")
                        && record.minAlter >= 25 && record.maxAlter <= 35;
            }
        });
        assert(newList.size() == 2);
    }

    // Statt anonymer Klasse wäre auch eine innere Klasse möglich:
    public class ListPredicateForRecords<T extends Record> implements ListPredicate<T> {

        @Override
        public boolean test(T record) {
            return record.geschlecht.equals(Citizens.SEX_M)
                    && record.staatsangehörigkeit.equals("D")
                    && record.ortsteilName.equals("Charlottenburg")
                    && record.minAlter >= 25 && record.maxAlter <= 35;
        }
    }
}
