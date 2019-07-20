package deadlocks;

public class Deadlock1 implements Runnable {

	private boolean done = false;
	
	@Override
	public synchronized void run() {
		while (!done) {
			doSomething();
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
	
	private void doSomething() {
		System.out.println("Thread tut etwas.");
	}
	
	public synchronized void setDone(boolean b) {
		done = b;
	}

	public static void main(String[] args) throws InterruptedException {
		Deadlock1 deadlock = new Deadlock1();
		Thread thread = new Thread(deadlock);
		thread.start();
		Thread.sleep(4000);
		deadlock.setDone(true);
	}

}
