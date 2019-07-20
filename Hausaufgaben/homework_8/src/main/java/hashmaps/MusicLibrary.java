package hashmaps;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

/**
 * TODO
 */
public class MusicLibrary extends HashMap<AudioFile, Song> {

    public MusicLibrary(Path path) {
        recursiveAudioFileSearch(path);
    }

    public void recognizeAudioFile(AudioFile audioFile) {
        System.out.println(this.get(audioFile).toString());
        /*
        get() auf HashMap gibt bereits Element der HashMap zurück, welches gleichen Hash-Code hast.
         */
    }

    /**
     * Adds all available songs with their corresponding audio files
     * to the hash map.
     * @param path
     * @return void
     */
    private void recursiveAudioFileSearch(Path path) {
        File[] files = path.toFile().listFiles();
        // System.out.println(path.toFile().getAbsolutePath()+path.toFile().isDirectory()+path.toFile().isFile()+path.toFile().exists());
        for (File file: files) {
            if (file.isDirectory()) {
                recursiveAudioFileSearch(file.toPath());
            } else {
                String[] tempSong = file.getName().split("-");
                this.put(new AudioFile(file.getPath()), new Song(tempSong[0], tempSong[1], tempSong[2]));
            }
        }
    }

    public static Path knownSongs()  {
        return Paths.get("src", "main", "resources", "knownSongs");
    }

    public static Path unknownSongs() {
        return Paths.get("src", "main", "resources", "unknownSongs");
    }

}
