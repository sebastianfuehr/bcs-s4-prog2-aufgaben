package streams;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamsTest {

    @Test
    public void testOrder() {
        Stream.of("d2", "a2", "b1", "b3", "c", "a6")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); })
                .filter(s -> { System.out.println("filter: " + s); return s.startsWith("A");
                })
                .limit(2)
                .forEach(s -> System.out.println("forEach: " + s));
    }
}
