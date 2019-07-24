package hashmaps;

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
	
	public String toString(){
		return " '"+this.title + "' from '" + this.album + "' by '"+ this.artist+"'";
	}
}
