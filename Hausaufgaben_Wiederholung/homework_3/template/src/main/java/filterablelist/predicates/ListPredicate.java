package filterablelist.predicates;

public interface ListPredicate<T> {
    boolean test(T recordOfAnyType);
}
