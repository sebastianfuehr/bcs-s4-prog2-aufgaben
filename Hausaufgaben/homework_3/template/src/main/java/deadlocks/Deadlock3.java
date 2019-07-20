package deadlocks;

import java.util.LinkedList;

public class Deadlock3 implements Runnable {
	
	LinkedList<Integer> intList;
	
	public Deadlock3(LinkedList<Integer> list) {
		intList = list;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		try {
			while (System.currentTimeMillis() - startTime < 10000) {
				synchronized (intList) {
					Integer number = intList.removeFirst();
					System.out.println(number + " removed");
					number = (number + 3) % 21;
					intList.addLast(number);
					System.out.println(number + " added");
					intList.wait();
					intList.notifyAll(); // notify wird von keinem Thread je erreicht und kann deshalb nie die
										 // bereits wartenden Thread benachrichtigen
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		for (int i = 0; i < 4; i++) {
			new Deadlock3(list);
		}
	}

}
