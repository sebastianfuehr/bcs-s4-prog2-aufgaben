package hashmaps;

import java.io.File;

public class MusicRecognizer {

	public static void main(String[] args) {
		MusicLibrary myLibrary = new MusicLibrary(MusicLibrary.knownSongs());

		File[] files = MusicLibrary.unknownSongs().toFile().listFiles();
		for (File file: files) {
			myLibrary.recognizeAudioFile(new AudioFile(file.getPath()));
		}
	}
}
