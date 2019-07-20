package standardfunctionalinterfaces;

import java.time.LocalDateTime;
import java.util.function.*;

public class StandardFunctionalInterfacesClass {

    public static void main(String[] args) {
        // 1
        BiPredicate<String, String> fun1 = (String s1, String s2) -> (s1.length() < s2.length()) ? true : false;
        // 2
        final double d = 10;
        DoubleSupplier fun2 = () -> d*d;
        // 3
        ToDoubleBiFunction<Integer, Integer> fun3 = (Integer i1, Integer i2) -> Math.PI * i1 * i2;
        // 4
        ToPrimitiveDoubleBiFunction fun4 = (int i1, int i2) -> Math.PI * i1 * i2;
        // 5
        Consumer<String> fun5 = c -> {
            String str = c.toString();
            String[] strarr = str.split("\\.");
            System.out.println(strarr.length);
        };
        // 6
        TriConsumer<String, String, String> fun6 = (a, b, c) -> System.out.println(
                a.toString() + b.toString() + c.toString()
        );
        // 7
        BooleanSupplier fun7 = () -> {
            LocalDateTime time = LocalDateTime.now();
            int minute = time.getMinute();
            if (minute%2 == 1) return true;
            else return false;
        };
    }

}
