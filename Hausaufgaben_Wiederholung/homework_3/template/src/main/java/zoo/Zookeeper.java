package zoo;

import java.util.concurrent.ThreadLocalRandom;

public class Zookeeper extends Inhabitant {

    public Zookeeper(String name) {
        super(name);
    }

    @Override
    void moveToFeeder() {
        zoo.zookeeperBreakRoom.remove(this);
        zoo.feedingRoom.add(this);
    }

    @Override
    void moveToCompound() {
        zoo.feedingRoom.remove(this);
        zoo.zookeeperBreakRoom.add(this);
    }

    @Override
    void feeding() throws InterruptedException {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        getZookeeperSemaphore().acquire(); // can throw Interrupted Exception
        getGazelleSemaphore().acquire(2);
        getLionSemaphore().acquire();
        moveToFeeder();
        zoo.feedingRoom.checkInvariants();
        Thread.sleep(rng.nextInt(5)); // mampf
        moveToCompound();
        getZookeeperSemaphore().release();
        getGazelleSemaphore().release(2);
        getLionSemaphore().release();
    }
}
