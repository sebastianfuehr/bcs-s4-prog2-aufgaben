package database;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

/**
 * Performs a Fork-Join filter
 *
 * @param <T> type of elements to be filtered
 */
class FilterTask<T> extends RecursiveTask<List<T>> {
    private List<T> list;
    private Predicate<T> predicate;
    private final static int threshold = 100;

    public FilterTask(List<T> list, Predicate<T> predicate) {
        this.list = list;
        this.predicate = predicate;
    }

    @Override
    protected List<T> compute() {
        return null;
    }
}
