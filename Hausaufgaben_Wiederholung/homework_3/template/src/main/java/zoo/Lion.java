package zoo;

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
        zoo.lionCompound.add(this);
    }

    @Override
    boolean canFeed() {
        return true; // TODO
    }
}
