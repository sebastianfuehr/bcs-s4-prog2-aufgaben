package hashmaps;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * The AudioFile represents a music file from the filesystem
 *
 */
public class AudioFile {
	private int hash;
	private String filename;
	
	/**
	 * The constructor returns a new Instance of AudioFile based on the given path
	 * @param path The path from where to load the file
	 */
	public AudioFile(String path){
		this.filename = path;
		computeHash();
	}
	
	/**
	 * computes a shortened md5-hash of the AudioFile
	 */
	private void computeHash(){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return;
		}
		
		try{
			InputStream is = Files.newInputStream(Paths.get(filename));
			DigestInputStream dis = new DigestInputStream(is, md);
			while(dis.read()!=-1){}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] digest = md.digest();
		hash = digest[0];
		hash += digest[1] << 8;
		hash += digest[2] <<16;
		hash += digest[3] <<24;
	}
	
	/**
	 * 
	 * @return the path to the AudioFile on the filesystem
	 */
	public String getFilename(){
		return this.filename;
	}
	
	public int hashCode(){
		return this.hash;
	}
	
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		else if(!AudioFile.class.isAssignableFrom(obj.getClass()))
			return false;
		else 
			return this.hash == ((AudioFile)obj).hash;
	}

}
