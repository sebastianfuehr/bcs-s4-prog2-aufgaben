package zoo;

import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ZooTest {

    @Test
    public void testZoo() {
        final int lionCount = 8;
        final int gazelleCount = 20;
        final int zookeeperCount = 3;

        Set<Lion> lions = new HashSet<>();
        for (String lionFirstName : generateFirstNames(lionCount)) {
            lions.add(new Lion(lionFirstName));
        }
        
        Set<Gazelle> gazelles = new HashSet<>();
        for (String gazelleFirstName : generateFirstNames(gazelleCount)) {
            gazelles.add(new Gazelle(gazelleFirstName));
        }

        Set<Zookeeper> zookeepers = new HashSet<>();
        for (String zookeeperFirstName: generateFirstNames(zookeeperCount)) {
            zookeepers.add(new Zookeeper(zookeeperFirstName));
        }
        
        // Create zoo
        Zoo zoo = new Zoo(lions, gazelles, zookeepers);
        zoo.start(); // Run the zoo for a while
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zoo.interrupt();
        zoo.feedingRoom.printStats();

        assertThat( // at one time one lion should have been in the feeder
                zoo.feedingRoom.oneLion,
                greaterThan(0)
        );
        assertThat( // at one time one gazelle should have been in the feeder
                zoo.feedingRoom.oneGazelle,
                greaterThan(0)
        );
        assertThat( // at one time two gazelles should have been in the feeder
                zoo.feedingRoom.twoGazelles,
                greaterThan(0)
        );
        assertThat( // at one time one zookeeper should have been in the feeder
                zoo.feedingRoom.oneZookeeper,
                greaterThan(0)
        );
    }

    private List<String> generateFirstNames(int count) {
        List<String> firstNames = new ArrayList<>(count);
        for (Name name : new NameGenerator().generateNames(count)) {
            firstNames.add(name.getFirstName());
        }
        return firstNames;
    }
}
