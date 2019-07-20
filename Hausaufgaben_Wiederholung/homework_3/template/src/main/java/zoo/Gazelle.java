package zoo;

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
    boolean canFeed() {
        return true; // TODO
    }
}
