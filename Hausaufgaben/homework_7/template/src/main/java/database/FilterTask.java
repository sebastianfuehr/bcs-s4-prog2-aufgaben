package database;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
        if (list.size() > threshold) { // split problem in half, because it's to big
            List<FilterTask<T>> newList = splitProblem();
            // fork sub-tasks (method declared in ForkJoinTask which extends into RecursiveTask)
            newList.forEach(FilterTask::fork);
            List<List<T>> listOfList = newList.stream().map(ForkJoinTask::join).collect(Collectors.toList());
            List<T> list = listOfList.get(0);
            list.addAll(listOfList.get(1));
            return list.stream().filter(predicate).collect(Collectors.toList());

            /*
            return filterTaskList.stream().map(x -> x.join()).reduce(new Array<>(), (acc, next) -> {
                acc.addAll(next);
                return acc;
            });
             */
            /*
            List<T> newFilterTask = new ArrayList<>();
            return newList.stream().map(FilterTask::join).forEach(newFilterTaks::addAll);
             */
        } else { // problem already small enough and doesn't need to be splitted
            return list.stream().filter(predicate).collect(toList());
        }
    }

    /*
     * Zerteilt die Liste in zwei ca. gleich große Hälften.
     */
    private List<FilterTask<T>> splitProblem() {
        int half = list.size() / 2;
        FilterTask<T> left = // left sub-list
                new FilterTask<>(list.subList(0, half), predicate);
        FilterTask<T> right = // right sub-list
                new FilterTask<>(list.subList(half, list.size()), predicate);
        return Arrays.asList(left, right);
    }
}
