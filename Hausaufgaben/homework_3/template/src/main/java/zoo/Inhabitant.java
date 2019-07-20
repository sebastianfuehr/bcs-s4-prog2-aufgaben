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
     * The behavior of a zoo inhabitant.
     */
    @Override
    public void run() {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        feed();
    }

    /**
     * Out sourced code of moving into the feeding room and leaving it
     * after a while.
     */
    public void feast() {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        try {
            moveToFeeder();
            zoo.feedingRoom.checkInvariants();
            Thread.sleep(rng.nextInt(5)); // mampf
            moveToCompound();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method waits for a semaphor to be released and then
     * moves the specific inhabitant into the feeding room. After
     * leaving the feeding room, it releases the semaphors.
     */
    abstract void feed();

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
}
