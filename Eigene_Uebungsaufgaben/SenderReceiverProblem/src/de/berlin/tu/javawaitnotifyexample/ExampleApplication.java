package de.berlin.tu.javawaitnotifyexample;

import de.berlin.tu.Data;

/**
 * java-wait-notify tutorial from https://www.baeldung.com/java-wait-notify
 */
public class ExampleApplication {

    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        sender.start();
        Thread receiver = new Thread(new Receiver(data));
        receiver.start();
    }

}
