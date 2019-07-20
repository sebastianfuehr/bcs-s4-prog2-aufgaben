package zoo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract superclass for a zoo inhabitant.
 */
abstract class Inhabitant implements Runnable {
    private final String typeName = getClass().getSimpleName().toLowerCase();
    private final String name;
    protected Zoo zoo;

    /**
     * Construct with a name
     *
     * @param name the name
     */
    public Inhabitant(String name) {
        this.name = name;
    }

    /**
     * Returns a name of an inhabitant.
     *
     * @return the name
     */
    public String getName() {
        return String.format("%s, the %s", name, typeName);
    }

    /**
     * Moves an {@link Inhabitant} to the feeding room.
     */
    abstract void moveToFeeder();

    /**
     * Moves an {@link Inhabitant} back to the compound.
     */
    abstract void moveToCompound();

    /**
     * Indicates if this inhabitant may enter the {@link Zoo#feedingRoom}.
     * 
     * @return
     */
    abstract boolean canFeed();

    /**
     * The behavior of a zoo inhabitant.
     */
    @Override
    public void run() {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        Zoo zoo = getZoo();
        try {
            while(!Thread.interrupted()) {
                if(canFeed()) {
                    moveToFeeder();
                    zoo.feedingRoom.checkInvariants();
                    Thread.sleep(rng.nextInt(5)); // mampf
                    moveToCompound();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
}
