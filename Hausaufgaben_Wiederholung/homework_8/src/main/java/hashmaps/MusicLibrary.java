package hashmaps;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * TODO
 */
public class MusicLibrary extends HashMap<AudioFile, Song> {
    public MusicLibrary(Path path) {
        // TODO
    }

    public static Path knownSongs()  {
        return Paths.get("src", "main", "resources", "knownSongs");
    }

    public static Path unknownSongs() {
        return Paths.get("src", "main", "resources", "unknownSongs");
    }


}
