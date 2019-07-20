package zoo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a gazelle in the zoo.
 */
public class Gazelle extends Inhabitant {

    public Gazelle(String name) {
        super(name);
    }

    @Override
    void moveToFeeder() {
        zoo.gazelleCompound.remove(this);
        zoo.feedingRoom.add(this);
    }

    @Override
    void moveToCompound() {
        zoo.feedingRoom.remove(this);
        zoo.feedingRoom.getGazelleSem().release();
        zoo.gazelleCompound.add(this);
    }

    @Override
    void feed() {
        try {
            zoo.feedingRoom.getGazelleSem().acquire();
            feast();
        } catch(InterruptedException e) {
            zoo.feedingRoom.getGazelleSem().release();
        }
    }
}
