package deadlocks;

public class Deadlock1 implements Runnable {

	private boolean done = false;
	
	@Override
	public synchronized void run() {
		while (!done) {
			synchronized (this) {
				if (done) break;
			}
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

	public void setDone(boolean b) {
		synchronized (this) {
			done = b;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Deadlock1 deadlock = new Deadlock1();
		Thread thread = new Thread(deadlock);
		thread.start();
		Thread.sleep(4000);
		deadlock.setDone(true);
	}

	/*
	 * Lösung:
	 * Die Methoden setDone und run sind beide synchronized. Dadurch kann die
	 * setDone Methode nicht aufgerufen werden, weil die run Methode gerade
	 * in einer Endlosschleife läuft.
	 * NIMALS SYNCHRONIZED AUF DIE GESAMTE RUN METHODE!!!
	 */

}
