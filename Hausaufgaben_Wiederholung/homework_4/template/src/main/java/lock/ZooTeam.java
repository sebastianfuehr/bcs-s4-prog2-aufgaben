package lock;

import java.util.Random;

public class ZooTeam  extends NewsTeam{
	
	String[] animals = {"tiger", "giraffe", "polar bear", "elephant"};

	
	public ZooTeam(NewsTicker t) {
		super(t);
	}




	@Override
	protected String getLatestNews() {
		Random r = new Random();
		String animal = animals[r.nextInt(animals.length)];
		
		String message = "Latest zoo news: " + "super cute "+ animal +" baby was born today.";
		
		return message;
	}

}
