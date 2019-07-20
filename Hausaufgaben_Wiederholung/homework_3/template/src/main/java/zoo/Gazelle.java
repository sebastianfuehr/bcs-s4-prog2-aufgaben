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
        zoo.gazelleCompound.add(this);
    }

    @Override
    void feeding() throws InterruptedException {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        getGazelleSemaphore().acquire(); // can throw Interrupted Exception
        moveToFeeder();
        zoo.feedingRoom.checkInvariants();
        Thread.sleep(rng.nextInt(5)); // mampf
        moveToCompound();
        getGazelleSemaphore().release();
    }
}
