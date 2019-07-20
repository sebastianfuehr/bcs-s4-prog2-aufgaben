package threadstate;

public class StateDemonstrator {

	public static void main(String[] args) throws InterruptedException {

		// A
		System.out.println("\nA:");
		
		Thread mythread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()) {
					// calculation
				}
			}
		});

		System.out.println(mythread.getState().name());

		mythread.start();

		System.out.println(mythread.getState().name());

		mythread.interrupt();

		Thread.sleep(10);
		System.out.println(mythread.getState().name());

		// B
		System.out.println("\nB:");
		
		Thread mySleepingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println(mySleepingThread.getState().name());

		mySleepingThread.start();
		
		Thread.sleep(100);
		System.out.println(mySleepingThread.getState().name());
		
		// C
		System.out.println("\nC:");
		
		Runnable syncRunnable = new Runnable() {
			@Override
			public void run() {
				mySyncMethod();
			}
		};
		Thread syncThread1 = new Thread(syncRunnable);
		Thread syncThread2 = new Thread(syncRunnable);
		syncThread1.start();
		syncThread2.start();
		Thread.sleep(100);
		System.out.println("1:" + syncThread1.getState().name());
		System.out.println("2:" + syncThread2.getState().name());
		
		// D
		System.out.println("\nD:");
		
		Runnable waitRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					myWaitMethod();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread waitThread1 = new Thread(waitRunnable);
		Thread waitThread2 = new Thread(waitRunnable);
		waitThread1.start();
		waitThread2.start();
		Thread.sleep(100);
		System.out.println("1:" + waitThread1.getState().name());
		System.out.println("2:" + waitThread2.getState().name());
		
		// E
		System.out.println("\nE:");
		
		Runnable notifyRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					myNotifyMethod();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread notifyThread1 = new Thread(notifyRunnable, "#1");
		Thread notifyThread2 = new Thread(notifyRunnable, "#2");
		notifyThread1.start();
		Thread.sleep(100);
		System.out.println("1:" + notifyThread1.getState().name());
		notifyThread2.start();
		Thread.sleep(100);
		System.out.println("1:" + notifyThread1.getState().name());
		Thread.sleep(500);
		System.out.println("1:" + notifyThread1.getState().name());
		Thread.sleep(500);
		System.out.println("1:" + notifyThread1.getState().name());
		
	}
	
	static Object object = new Object();
	public static void mySyncMethod(){
		synchronized (object) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static Object object2 = new Object();
	public static void myWaitMethod() throws InterruptedException{
		synchronized (object2) {
			object2.wait();
		}
	}
	
	static Object object3 = new Object();
	public static void myNotifyMethod() throws InterruptedException{
		synchronized (object3) {
			if (Thread.currentThread().getName().equals("#1")) {
				object3.wait();
				Thread.sleep(500);
			}
			if (Thread.currentThread().getName().equals("#2")) {
				object3.notify();
				Thread.sleep(500);
			}
		}
	}

}
