package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override // 7.1
    protected List<U> compute() {
        if(list.size() > THRESHOLD) { // we want to split this
            List<MapTask<T, U>> reduceTasks = splitProblem(); // returns List<MapTask<T, U>>
            reduceTasks.forEach(MapTask::fork); // fork sub-tasks
            return reduceTasks.stream() // join subtasks and return the mapping
                    .map(MapTask::join)
                    .reduce(new ArrayList<>(), (acc, next) -> {
                        acc.addAll(next);
                        return acc;
                    });
        } else { // the problem is "small enough", let's reduce
            return list.stream().map(mapper).collect(Collectors.toList());
        }
    }

    private List<MapTask<T, U>> splitProblem() {
        int half = list.size() / 2;
        MapTask<T, U> left = // left sub-list
                new MapTask<>(list.subList(0, half), mapper);
        MapTask<T, U> right = // right sub-list
                new MapTask<>(list.subList(half, list.size()), mapper);
        return Arrays.asList(left, right);
    }
}
