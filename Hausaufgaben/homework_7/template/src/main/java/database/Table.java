package database;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Table and useful analysis methods.
 *
 * @param <T> The type of the elements in the table.
 */
public class Table<T> implements Iterable<T> {
    private final List<T> list;

    public Table(List<T> list) {
        this.list = list;
    }

    public Table<T> filter(Predicate<T> predicate) {
        return new Table<>(
                new ForkJoinPool().invoke(new FilterTask<>(this.list, predicate))
        );
    }

    public <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner) {
        return new ForkJoinPool()
                .invoke(new ReduceTask<>(this.list, accumulator, combiner, identity));
    }

    public <U> Table<U> map(Function<T, U> function) {
        return new Table<>(
                new ForkJoinPool().invoke(new MapTask<>(this.list, function))
        );
    }

    /**
     * The underlying iterator (so we can use the foreach syntax)
     *
     * @return an iterator for this collection
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    /**
     * Returns the size of {@link this#list}
     * 
     * @return
     */
    public int size() {
        return list.size();
    }
}
