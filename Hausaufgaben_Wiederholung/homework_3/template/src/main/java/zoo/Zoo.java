package zoo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This class runs the zoo. 
 */
public class Zoo extends Thread {
    private final Collection<Thread> threads;

    final Set<Lion> lionCompound;
    final Set<Gazelle> gazelleCompound;
    final FeedingRoom feedingRoom = new FeedingRoom();

    /**
     * Set up the zoo with a set of lions and a set of gazelles.
     *
     * @param lionCompound a set of lions
     * @param gazelleCompound a set of gazelles
     */
    public Zoo(Set<Lion> lionCompound, Set<Gazelle> gazelleCompound) {
        this.lionCompound = lionCompound;
        this.gazelleCompound = gazelleCompound;
        List<Thread> threads = new ArrayList<>(lionCompound.size() + gazelleCompound.size());
        for (Lion lion : lionCompound) {
            lion.setZoo(this);
            threads.add(new Thread(lion, lion.getName()));
        }
        for (Gazelle gazelle : gazelleCompound) {
            gazelle.setZoo(this);
            threads.add(new Thread(gazelle, gazelle.getName()));
        }
        this.threads = threads;
    }

    @Override
    public void run() {
        for (Thread thread1 : threads) {
            thread1.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {

        }
    }
}
