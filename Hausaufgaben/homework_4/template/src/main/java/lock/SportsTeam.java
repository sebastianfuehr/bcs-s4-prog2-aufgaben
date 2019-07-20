package lock;

import java.util.Random;

public class SportsTeam extends NewsTeam {
	public SportsTeam(NewsTicker t) {
		super(t);
	}

	String[] teams = {"DuckTown Duckies", "Smurphies", "Telepathic Teletubbies", "Crazy Cranberrys", "Alltime Animals"};

	@Override
	protected String getLatestNews() {
		Random r = new Random();
		String team1 = teams[r.nextInt(teams.length)];
		String team2 = teams[r.nextInt(teams.length)];
		int score1 = r.nextInt(5);
		int score2 = r.nextInt(5);

		String message;
		message = "ST - Latest results: " + team1 + " vs. " + team2 + " (" + score1 + ":" + score2 + ")";

		return message;
	}

}
