package hashmaps;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MusicRecognizer {

	private static final String RESOURCES_PATH = "/home/krokodiledandy/tubCloud/Studium/S4_Prog2/Intellij-Projects/Hausaufgaben_Wiederholung/homework_8/src/main/resources/";

	public static void main(String[] args) {
		MusicLibrary knownSongs = new MusicLibrary(Paths.get(RESOURCES_PATH+"/knownSongs"));
		File[] unknownSongs = Paths.get(RESOURCES_PATH+"/unknownSongs").toFile().listFiles();
		System.out.println("List of Songs which where found in /unknownSongs:");
		for (File file: unknownSongs) {
			knownSongs.recognizeAudioFile(new AudioFile(file.toString()));
		}
	}
}
