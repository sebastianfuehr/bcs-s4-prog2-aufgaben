package database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Table and useful analysis methods.
 *
 * @param <T> The type of the elements in the table.
 */
public class Table<T> implements Iterable<T> {
    private final Collection<T> collection;

    public Table(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * Filter this list with a given predicate
     *
     * @param predicate the predicate
     * @return a NEW table with the filtered result.
     */
    public Table<T> filter(Predicate<T> predicate) {
        ArrayList<T> newList = new ArrayList<>();
        for (T t : collection) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return new Table<>(newList);
    }

    // Was funktioniert hierbei nicht?
    // return collection.stream().reduce(aggregation.initial(), (acc, value) -> aggregation.apply(acc, value));
    /**
     * Reduces this table down to one value by iteratively calling <pre>apply</pre>.
     * Starting with the <pre>initial</pre> value (see {@link Aggregation})
     *
     * @param aggregation the aggregation
     * @param <R> The return type (supplied by the Aggregation). Is also the initial type.
     * @return The result of the aggregation.
     */
    public <R> R reduce(Aggregation<T, R> aggregation) {

        R currResult = aggregation.initial();
        for (T el: collection) {
            currResult = aggregation.apply(currResult, el);
        }
        return currResult;
    }

    /**
     * The Aggregation interface
     *
     * @param <T> The type of the elements to be transformed (typically the same as the Table's T type)
     * @param <R> The return type (could be anything)
     */
    interface Aggregation<T, R> {
        /**
         * The accumulator's initial value.
         *
         * @return
         */
        R initial(); // the initial value

        /**
         * Returns the next accumulator value
         *
         * @param accumulator the current accumulator value
         * @param next the next element
         * @return the next accumulator value
         */
        R apply(R accumulator, T next);
    }

    /**
     * Provides the test function, indicating
     *
     * @param <T> will test on this type
     */
    interface Predicate<T> {
        boolean test(T t);
    }

    /**
     * The underlying iterator (so we can use the foreach syntax)
     *
     * @return an iterator for this collection
     */
    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    /**
     * Returns the size of {@link this#collection}
     * 
     * @return
     */
    public int size() {
        return collection.size();
    }
}
