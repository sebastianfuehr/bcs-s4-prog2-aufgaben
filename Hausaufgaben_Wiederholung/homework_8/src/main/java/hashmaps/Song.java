package hashmaps;

import java.nio.file.Paths;

/**
 *  Represents a musical art piece. It is described by the title, artist and the album
 *
 */
public class Song {
	
	private String artist;
	private String title;
	private String album;
	
	/**
	 * The constructor creates a new song instance
	 * @param artist Is the artist who performed the song
	 * @param album Is the album that contains this version of the song
	 * @param title Is the title of the song
	 */
	public Song (String artist,  String album, String title){
		this.album = album;
		this.artist = artist;
		this.title = title;
	}

	/**
	 * The constructor creates a new song instance from an AudioFile instance.
	 * @param audioFile The audio file with the song.
	 */
	public Song(AudioFile audioFile) {
		String filename = Paths.get(audioFile.getFilename()).toFile().getName();
		String[] temp = filename.split("-");
		this.album = temp[1];
		this.artist = temp[0];
		this.title = temp[2];
		removeFormat();
	}

	/** Removes underscores and the .mp3 file extension from the song attributes. */
	public void removeFormat() {
		this.album = this.album.replace("_", " ");
		this.artist = this.artist.replace("_", " ");
		this.title = this.title.replace("_"," ").split("\\.")[0];
	}
	
	public String toString(){
		return " '"+this.title + "' from '" + this.album + "' by '"+ this.artist+"'";
	}
}
