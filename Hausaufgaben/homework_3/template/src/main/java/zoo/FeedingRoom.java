package zoo;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.Semaphore;

/**
 * Represents the shared feeding room. 
 */
class FeedingRoom {
    final Set<Inhabitant> feeding = new HashSet<>();
    int oneLion = 0, oneGazelle = 0, twoGazelles = 0, oneZooKeeper = 0; // Statistics
    private Semaphore lionSem = new Semaphore(1),
                      gazelleSem = new Semaphore(2),
                      zooKeeperSem = new Semaphore(1);

    /**
     * Method checks for the validity of the current state.
     * Please note, that this method may be prone (empfänglich) to race conditions.
     */
    void checkInvariants() {
        long lionCount = 0L;
        for (Inhabitant i : feeding) {
            if (i instanceof Lion) {
                lionCount++;
            }
        }
        long gazelleCount = 0L;
        for (Inhabitant i : feeding) {
            if (i instanceof Gazelle) {
                gazelleCount++;
            }
        }
        long zooKeeperCount = 0L;
        for (Inhabitant i: feeding) {
            if (i instanceof Zookeeper) {
                zooKeeperCount++;
            }
        }
        if (lionCount > 0 && gazelleCount > 0) {
            System.out.println("Carnage!");
            print();
        } else if (lionCount > 1) {
            System.out.println("Too many lions!");
            print();
        } else if (gazelleCount > 2) {
            System.out.println("Too many gazelles!");
            print();
        } else if (gazelleCount == 1) {
            oneGazelle += 1;
        } else if(gazelleCount == 2) {
            twoGazelles += 1;
        } else if (lionCount == 1) {
            oneLion += 1;
        } else if (zooKeeperCount == 1) {
            oneZooKeeper += 1;
        }
    }

    /**
     * Prints statistics for the feeding room. Please note that
     * the results may be inaccurate due to race conditions, but if an event
     * happened at least once, the count should be greater than 0!
     */
    final public void printStats() {
        System.out.println("Stats:");
        System.out.println("======");
        System.out.printf("One lion: %d\n", oneLion);
        System.out.printf("One gazelle: %d\n", oneGazelle);
        System.out.printf("Two Gazelles: %d\n", twoGazelles);
        System.out.printf("One Zookeeper: %d\n", oneZooKeeper);
        System.out.println();
    }

    /**
     * Print the current status of inhabitants in the feeder.
     */
    final private void print() {
        StringJoiner joiner = new StringJoiner(" and ");
        for (Inhabitant inhabitant : feeding) {
            String name = inhabitant.getName();
            joiner.add(name);
        }
        System.out.println("In Feeder: " +
                joiner.toString()
        );
    }

    /**
     * Add a lion to the feeder.
     *
     * @param l the lion
     */
    void add(Lion l) {
        feeding.add(l);
    }

    /**
     * Remove a lion from the feeder.
     * @param l the lion
     */
    void remove(Lion l) {
        feeding.remove(l);
    }

    /**
     * Add a gazelle to the feeder
     *
     * @param g the gazelle
     */
    void add(Gazelle g) {
        feeding.add(g);
    }

    /**
     * Remove a gazelle from the feeder
     *
     * @param g the gazelle.
     */
    void remove(Gazelle g) {
        feeding.remove(g);
    }

    void add(Zookeeper z) {feeding.add(z);}
    void remove(Zookeeper z) {feeding.remove(z);}

    public synchronized Semaphore getLionSem() {
        return lionSem;
    }

    public synchronized Semaphore getGazelleSem() {
        return gazelleSem;
    }

    public synchronized Semaphore getZooKeeperSem() {return zooKeeperSem;}
}
