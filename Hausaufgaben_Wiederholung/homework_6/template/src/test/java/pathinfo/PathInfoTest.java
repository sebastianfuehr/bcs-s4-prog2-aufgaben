package pathinfo;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class PathInfoTest {
    @Test
    public void testListFiles() {
        String dir = ".";
        Path path = Paths.get(dir);
        System.out.printf("Listing for `%s`\n", dir);
        System.out.println("==================");
        PathInfo.list(path);
    }

    @Test
    public void testDiskUsage() {
        String dir = ".";
        long length = PathInfo.diskUsage(Paths.get(dir));
        assertThat(length, greaterThan(0L));
        System.out.printf("Size of directory `%s` : %d KiB\n", dir, length / 1024);
    }
}
