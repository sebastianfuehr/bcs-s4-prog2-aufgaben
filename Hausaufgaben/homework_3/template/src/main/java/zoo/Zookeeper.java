package zoo;

public class Zookeeper extends Inhabitant {
    /**
     * Construct with a name
     *
     * @param name the name
     */
    public Zookeeper(String name) {
        super(name);
    }

    @Override
    void moveToFeeder() {
        zoo.zooKeeperRoom.remove(this);
        zoo.feedingRoom.add(this);
    }

    @Override
    void moveToCompound() {
        zoo.feedingRoom.remove(this);
        zoo.feedingRoom.getZooKeeperSem().release();
        zoo.feedingRoom.getGazelleSem().release();
        zoo.feedingRoom.getLionSem().release();
        zoo.zooKeeperRoom.add(this);
    }

    @Override
    void feed() {
        try {
            zoo.feedingRoom.getZooKeeperSem().acquire();
            zoo.feedingRoom.getLionSem().acquire();
            zoo.feedingRoom.getGazelleSem().acquire();
            feast();
        } catch(InterruptedException e) {
            zoo.feedingRoom.getZooKeeperSem().release();
            zoo.feedingRoom.getGazelleSem().release();
            zoo.feedingRoom.getLionSem().release();
        }
    }
}
