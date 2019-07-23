package scala2java;

public class ScalaCodeInJava {

    // 1.
    public class Hello {}

    // 2.
    public class User {

        private final String n;
        public final String name;

        public User(String n) {
            this.n = n;
            this.name = n;
        }

        public String greet() {
            return String.format("Hello from %s", name);
        }

        @Override
        public String toString() {
            return String.format("User(%s)", name);
        }
    }

    // 3.
    public class Person {

        public String name = "";
        private Integer age = 0;

        public Person() {}

        public Person(String name) {
            this();
            this.name = name;
        }

        public Person(String name, int age) {
            this(name);
            this.age = age;
        }

    }

    // 4.
    public class PersonTwo {

        public String name;
        private Integer age;

        public PersonTwo(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

    }

}
