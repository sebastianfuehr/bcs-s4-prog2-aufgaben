package lock;

public class NewsRoom {

	public static void main(String[] args) {
		NewsTicker ticker = new NewsTicker();
		
		SportsTeam st = new SportsTeam(ticker);
		ZooTeam  zt = new ZooTeam(ticker);
		StockExchangeTeam set = new StockExchangeTeam(ticker);
		
		st.start();
		zt.start();
		set.start();
		
		try {
			st.join();
			zt.join();
			set.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

}
