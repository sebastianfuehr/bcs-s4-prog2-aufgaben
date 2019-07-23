package pathinfo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Arrays;

public class PathInfo {

    public static void main(String[] args) {
        String pathTemp = "./..";
        File file = new File(pathTemp);
        //list(file.toPath());
        System.out.println(diskUsage(file.toPath()));
    }

    /**
     * Traverses the file system recursively from a root path
     * and outputs the files.
     *
     * @param path the root path
     */
    public static void list(Path path) {
        if (path.toFile().isDirectory()) {
            File[] files = path.toFile().listFiles();
            for (File file: files) list(file.toPath());
        } else {
            System.out.println(path.toString());
        }
    }

    /**
     * Traverses the file system recursively from a root path and
     * computes the total size of all files.
     *
     * @param path
     * @return
     */
    public static long diskUsage(Path path) {
        return diskUsage(path, 0l);
    }

    private static long diskUsage(Path path, long fileSize) {
        if (path.toFile().isDirectory()) {
            File[] files = path.toFile().listFiles();
            for (File file: files) {
                fileSize += diskUsage(file.toPath());
            }
            return fileSize;
        } else {
            return fileSize + path.toFile().length();
        }
    }
}
