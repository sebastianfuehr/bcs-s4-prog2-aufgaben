package database;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * Performs a divide-and-conquer reduce
 *
 * @param <T> the list element type
 * @param <U> the identity and return type
 */
class ReduceTask<T, U> extends RecursiveTask<U> {
    private static final int THRESHOLD = 100;
    private U identity;
    private BiFunction<U, ? super T, U> accumulator;
    private BinaryOperator<U> combiner;
    private List<T> list;

    public ReduceTask(List<T> list, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner, U identity) {
        this.identity = identity;
        this.accumulator = accumulator;
        this.combiner = combiner;
        this.list = list;
    }

    @Override
    protected U compute() {
        if(list.size() > THRESHOLD) { // we want to split this
            List<ReduceTask<T, U>> reduceTasks = splitProblem();
            reduceTasks.forEach(ReduceTask::fork); // fork sub-tasks
            return reduceTasks.stream() // join subtasks and return the reduction
                    .map(ReduceTask::join)
                    .reduce(identity, combiner);
        } else { // the problem is "small enough", let's reduce
            return list.stream().reduce(identity, accumulator, combiner);
        }
    }

    private List<ReduceTask<T, U>> splitProblem() {
        int half = list.size() / 2;
        ReduceTask<T, U> left = // left sub-list
                new ReduceTask<>(list.subList(0, half), accumulator, combiner, identity);
        ReduceTask<T, U> right = // right sub-list
                new ReduceTask<>(list.subList(half, list.size()), accumulator, combiner, identity);
        return Arrays.asList(left, right);
    }
}
