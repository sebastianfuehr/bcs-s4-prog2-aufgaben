package database;

import scala.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Performs a Fork-Join filter
 *
 * @param <T> type of elements to be filtered
 */
class FilterTask<T> extends RecursiveTask<List<T>> {
    private List<T> list;
    private Predicate<T> predicate;
    private final static int THRESHOLD = 100;

    public FilterTask(List<T> list, Predicate<T> predicate) {
        this.list = list;
        this.predicate = predicate;
    }

    @Override // 7.1
    protected List<T> compute() {
        if(list.size() > THRESHOLD) { // we want to split this
            List<FilterTask<T>> filterTasks = splitProblem(); // returns List<FilterTask<T>>
            filterTasks.forEach(FilterTask::fork); // fork sub-tasks
            List<T> newList = new ArrayList<>();
            return filterTasks.stream() // join sub-tasks and return the filtered list -> was filtered before FilterTask::join
                    .map(FilterTask::join)
                    .reduce(newList, (acc, next) -> {
                       acc.addAll(next);
                       return acc;
                    });
        } else { // the problem is "small enough", let's filter
            return list.stream().filter(predicate).collect(Collectors.toList());
        }
    }

    private List<FilterTask<T>> splitProblem() {
        int half = list.size() / 2;
        FilterTask<T> left = // left sub-list
                new FilterTask<>(list.subList(0, half), predicate);
        FilterTask<T> right = // right sub-list
                new FilterTask<>(list.subList(half, list.size()), predicate);
        return Arrays.asList(left, right);
    }
}
