package citizenlist;

import citizens.Record;

import java.util.Iterator;
import java.util.List;

/**
 * This class encapsulates a list of records (representing a line in the table)
 *
 * If you like, you can change {@link Record} type to your own implementation!
 */
public class CitizenList implements Iterable<Record> {
    private final List<Record> recordList;

    /**
     * Creates a citizen list from a list of {@link Record}s.
     * 
     * @param recordList a List of {@link Record}s
     */
    public CitizenList(List<Record> recordList) {
        this.recordList = recordList;
    }

    /**
     * Returns the size of the list
     *
     * @return an int representing the size
     */
    public int size() {
        return recordList.size();
    }

    // TODO implement filter

    /**
     * Returns an iterator of records from the underlying list. 
     *
     * @return an {@link Iterator} over records.
     */
    @Override
    public Iterator<Record> iterator() {
        return recordList.iterator();
    }
}
