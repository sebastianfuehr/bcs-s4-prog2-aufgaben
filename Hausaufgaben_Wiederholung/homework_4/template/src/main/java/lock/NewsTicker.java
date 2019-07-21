package lock;

import java.util.concurrent.locks.ReentrantLock;

public class NewsTicker{
	ReentrantLock lock = new ReentrantLock();
	public NewsTicker(){
	}
	
	public void displayMessage(String message){
		lock.lock();
		System.out.println("\n\n\n\n\n\n\n\n\n");
		char[] m = message.toCharArray();
		for(char c : m){
			System.out.print(c);
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
		
}
