package sportreporter;

import java.util.Random;

/**
 * Enum class containing all possible football match events.
 * @author prog2-team
 *
 */
public enum SoccerEvent {
	
	FOUL("Foulspiel"), GOAL("Tor"), OFFSIDE("Abseits"), CORNER("Eckball"), TACKLING("Zweikampf");
	
	private final String catchword;
	
	SoccerEvent(String catchword){
		this.catchword = catchword;
	}
	
	/**
	 * @return returns the German word for the event
	 */
	public String getCatchword() {
		return catchword;
	}

}
