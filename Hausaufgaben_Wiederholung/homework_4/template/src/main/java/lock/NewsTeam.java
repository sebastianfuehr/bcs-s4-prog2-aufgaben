package lock;

import java.util.Random;

public abstract class NewsTeam extends Thread {
	private NewsTicker ticker;
	
	public NewsTeam(NewsTicker t){
		this.ticker = t;
	}
	
	public void run(){
		while(true){
			long amount = (long)(new Random().nextDouble() * 20000);
			try {
				this.sleep(amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String message =  getLatestNews();
			ticker.displayMessage(message);
			
		}

	}
	
	protected abstract String getLatestNews();
}
