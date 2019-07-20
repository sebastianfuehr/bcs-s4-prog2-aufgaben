package zoo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract superclass for a zoo inhabitant.
 */
abstract class Inhabitant implements Runnable {
    private final String typeName = getClass().getSimpleName().toLowerCase();
    private final String name;
    protected Zoo zoo;

    // Synchronisierte Objekte unbedingt private setzen! Sonst kann nicht sichergestellt werden, dass Zugriff nur über
    // synchronisierte getter-Methoden erfolgt!
    private static Semaphore gazSem = new Semaphore(2),
            lionSem = new Semaphore(1),
            zooKeeperSem = new Semaphore(1);

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
     * Move the inhabitant to and from the feeding room in a thread safe way.
     */
    abstract void feeding() throws InterruptedException;

    /**
     * The behavior of a zoo inhabitant.
     */
    @Override
    public void run() {
        Zoo zoo = getZoo();
        try {
            while(!Thread.interrupted()) {
                feeding();
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

    public synchronized Semaphore getLionSemaphore() {return lionSem;}

    public synchronized Semaphore getGazelleSemaphore() {return gazSem;}

    public synchronized Semaphore getZookeeperSemaphore() {return zooKeeperSem;}
}
