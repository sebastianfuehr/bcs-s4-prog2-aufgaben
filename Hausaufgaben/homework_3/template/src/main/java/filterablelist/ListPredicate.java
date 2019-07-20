package filterablelist;

public interface ListPredicate<T> {
    boolean test(T rec);
}
