package database;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

/**
 * TODO
 *
 * Performs a Fork-Join map
 *
 * @param <T> the original type
 * @param <U> the target type
 */
public class MapTask<T, U> extends RecursiveTask<List<U>> {
    private static final int THRESHOLD = 100;

    private List<T> list;
    private Function<T, U> mapper;


    public MapTask(List<T> list, Function<T, U> mapper) {
        this.list = list;
        this.mapper = mapper;
    }

    @Override
    protected List<U> compute() {
        return null;
    }
}
