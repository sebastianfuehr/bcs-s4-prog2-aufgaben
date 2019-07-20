package filterablelist;

import filterablelist.predicates.ListPredicate;

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

    public FilterableList<T> filter(ListPredicate<T> predicate) {
        List<T> newList = new ArrayList<T>();
        for (T entry: list) {
            if (predicate.test(entry)) newList.add(entry);
        }
        return new FilterableList<T>(newList);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
