package filterablelist;

import java.util.Iterator;
import java.util.List;

/**
 * A generic list, that can be filtered.
 *
 * @param <T> the type of list records.
 */
public class FilterableList<T> implements Iterable<T> {
    private final List<T> list;

    public FilterableList(List<T> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    // TODO implement filter

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
