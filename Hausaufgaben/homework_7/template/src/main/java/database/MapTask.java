package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Override
    protected List<U> compute() {
        if (list.size() > THRESHOLD) {
            List<MapTask<T, U>> newList =  splitProblem();
            newList.forEach(MapTask::fork);
            /*
            List<List<U>> listOf = newList.stream().map(MapTask::join).collect(Collectors.toList());
            List<U> list = listOf.get(0);
            list.addAll(listOf.get(1));
            return list;
             */
            return newList.stream().map(MapTask::join).reduce(new ArrayList<>(), (acc, next) -> {
                acc.addAll(next);
                return acc;
            });
            /*
            List<U> newMapTasks = new ArrayList<>();
            return newList.stream().map(MapTask::join).forEach(newMapTasks::addAll);
             */
            // Hinweis: Mit FlatMap arbeiten wäre auch möglich/gut gewesen.
        } else {
            return list.stream().map(mapper).collect(Collectors.toList());
        }
    }

    /*
     * Zerteilt die Liste in zwei ca. gleich große Hälften.
     */
    private List<MapTask<T, U>> splitProblem() {
        int half = list.size() / 2;
        MapTask<T, U> left = // left sub-list
                new MapTask<>(list.subList(0, half), mapper);
        MapTask<T, U> right = // right sub-list
                new MapTask<>(list.subList(half, list.size()), mapper);
        return Arrays.asList(left, right);
    }

}
