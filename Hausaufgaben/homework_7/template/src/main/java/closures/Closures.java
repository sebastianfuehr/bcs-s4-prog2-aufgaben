package closures;

import java.util.Scanner;
import java.util.function.Function;

public class Closures {

    static void greetings(String name){
        Function<String, String> greeter = new Function<String, String>() {
            @Override
            public String apply(String message) {
                return message + " " + name; // name kommt aus dem Kontext von der greetings-Methode
            }
        };
        /*
        Function<String, String> greeter = message -> message + " " + name;
         */
        welcomeMessage(greeter);
    }

    static void welcomeMessage(Function<String, String> greeter){
        System.out.println(greeter.apply("Hello"));
    }

    public static void main (String args[]){
        System.out.println("What's your name?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // nextLine() wartet auf Return-Taste (Enter)
        greetings(input);
    }
}
