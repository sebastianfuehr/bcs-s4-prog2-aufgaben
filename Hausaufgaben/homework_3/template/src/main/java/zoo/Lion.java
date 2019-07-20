package zoo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a lion in the zoo.
 */
class Lion extends Inhabitant {
    public Lion(String name) {
        super(name);
    }

    @Override
    void moveToFeeder() {
        zoo.lionCompound.remove(this);
        zoo.feedingRoom.add(this);
    }

    @Override
    void moveToCompound() {
        zoo.feedingRoom.remove(this);
        zoo.feedingRoom.getLionSem().release();
        zoo.feedingRoom.getGazelleSem().release(2);
        zoo.lionCompound.add(this);
    }

    @Override
    void feed() {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        try {
            zoo.feedingRoom.getLionSem().acquire();
            zoo.feedingRoom.getGazelleSem().acquire(2);
            feast();
        } catch(InterruptedException e) {
            zoo.feedingRoom.getLionSem().release();
            zoo.feedingRoom.getGazelleSem().release(2);
        }
    }
}
