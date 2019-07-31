package de.berlin.tu.javawaitnotifyexample;

import de.berlin.tu.Data;

public class Receiver implements Runnable {

    private Data load;

    public Receiver(Data data) {
        this.load = data;
    }

    @Override
    public void run() {
        for (String receivedMessage = load.receive();
            !"End".equals(receivedMessage);
            receivedMessage = load.receive()) {
                System.out.println(receivedMessage);

        }
    }
}
