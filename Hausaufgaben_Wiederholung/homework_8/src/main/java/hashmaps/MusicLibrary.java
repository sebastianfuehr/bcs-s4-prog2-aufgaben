package hashmaps;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 */
public class MusicLibrary extends HashMap<AudioFile, Song> {

    public MusicLibrary(Path path) throws NullPointerException {
        File[] files = path.toFile().listFiles();
        for (File file : files) {
            AudioFile audioFile = new AudioFile(file.toString());
            Song song = new Song(audioFile);
            this.put(audioFile, song);
        }
    }

    public static Path knownSongs()  {
        return Paths.get("src", "main", "resources", "knownSongs");
    }

    public static Path unknownSongs() {
        return Paths.get("src", "main", "resources", "unknownSongs");
    }

    public void printCollection() {
        Iterator iterator = this.values().iterator();
        while (iterator.hasNext()) {
            Song entry = (Song) iterator.next();
            System.out.println(entry.toString());
        }
    }

    public void recognizeAudioFile(AudioFile file) {
        Iterator iterator = this.keySet().iterator();
        while (iterator.hasNext()) {
            AudioFile audioFile = (AudioFile) iterator.next();
            if (audioFile.hashCode() == file.hashCode()) {
                Song song = new Song(audioFile);
                System.out.println(song);
                break;
            }
        }
    }


}
