package de.berlin.tu.javawaitnotifyexample;

import de.berlin.tu.Data;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {

    private Data data;

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String packets[] = {
                "packet 1",
                "packet 2",
                "packet 3",
                "packet 4",
                "End"
        };
        for (String packet: packets) {
            data.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
