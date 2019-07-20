package filterablelist;

import java.util.ArrayList;
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

    public FilterableList filter(ListPredicate<T> pred) {
        List<T> newList = new ArrayList<>();
        for (T rec: list) {
            if (pred.test(rec)) newList.add(rec);
        }
        return new FilterableList<T>(newList);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
