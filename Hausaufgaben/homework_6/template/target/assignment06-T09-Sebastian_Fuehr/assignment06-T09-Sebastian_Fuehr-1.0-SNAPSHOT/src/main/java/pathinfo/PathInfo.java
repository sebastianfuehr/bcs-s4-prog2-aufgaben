package pathinfo;

import java.nio.file.Path;
//import java.nio.file.File;
import java.nio.file.Files;

public class PathInfo {

    /**
     * Traverses the file system recursively from a root path
     * and outputs the files.
     *
     * TODO implement me!
     *
     * @param path the root path
     */
    public static void list(Path path) {
        /*
        File f = new File(path);
        System.out.println("???");
         */
    }

    /*
    private static File[] recList(File[] fileArray) {

    }
     */

    /**
     * Traverses the file system recursively from a root path and
     * computes the total size of all files.
     *
     * TODO implement me!
     *
     * @param path
     * @return
     */
    public static long diskUsage(Path path) {
        return -1;
    }
}
