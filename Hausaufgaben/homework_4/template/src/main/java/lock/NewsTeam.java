package lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public abstract class NewsTeam extends Thread {
	private NewsTicker ticker;
	private static final ReentrantLock lock = new ReentrantLock();
	
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
			lock.lock();
			try {
				ticker.displayMessage(message);
			} finally {
				lock.unlock();
			}
		}

	}
	
	protected abstract String getLatestNews();

}
