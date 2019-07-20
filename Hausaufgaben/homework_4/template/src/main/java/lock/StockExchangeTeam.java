package lock;

import java.text.DecimalFormat;
import java.util.Random;

public class StockExchangeTeam extends NewsTeam {
	DecimalFormat df = new DecimalFormat();
	String[] companies = {"PiedPiper", "Hooli", "InferTron", "EndFrame", "BachManity"};

	public StockExchangeTeam(NewsTicker t) {
		super(t);
		df.setMaximumFractionDigits(2);
		df.setPositivePrefix("+");
	}


	@Override
	protected String getLatestNews() {
		Random r = new Random();
		String company = companies[r.nextInt(companies.length)];
		boolean up = r.nextBoolean();
		float percent = up ? r.nextFloat()*20  : r.nextFloat()*-20;
		String action = up ? " on the rise ": " is crashing ";

		String message;
		message = "SET - Latest stock news: " + company + action + " : " + df.format(percent) + "%";

		return message;
	}

}
