package philosophers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a philosopher. He is doing the following (in this order):
 * <ul>
 * <li>thinking for 0-1 seconds</li>
 * <li>picks up his left chopstick (or waits until it is free)</li>
 * <li>picks up his right chopstick (or waits until it is free)</li>
 * <li>eats for 0-1 seconds</li>
 * <li>puts down his right chopstick</li>
 * <li>puts down his left chopstick</li>
 * </ul>
 * and repeats this.
 * @author prog2-team
 */
public class Philosopher implements Runnable {
	
	Chopstick leftChopstick;
	Chopstick rightChopstick;
	String name;
	Random random = new Random();
	
	public Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.name = name;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}

	@Override
	public void run() {
		try {
			while (true) {
				leftChopstick.lock.lock();
				leftChopstick.pickUp();
				System.out.println(this + " picks up his left chopstick");
				// to provoke a deadlock:
				Thread.sleep(1000);
				Thread.yield();
				Random ran = new Random();
				if (rightChopstick.lock.tryLock(ran.nextInt(1000), TimeUnit.MILLISECONDS)) {
					rightChopstick.pickUp();
					System.out.println(this + " picks up his right chopstick");
					eat();
					rightChopstick.putDown();
					rightChopstick.lock.unlock();
					System.out.println(this + " puts down his right chopstick");
				}
				leftChopstick.putDown();
				leftChopstick.lock.unlock();
				System.out.println(this + " puts down his left chopstick");
				think();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void think() throws InterruptedException {
		System.out.println(this + " is thinking");
		Thread.sleep(random.nextInt(1000));
	}
	
	private void eat() throws InterruptedException {
		System.out.println(this + " is eating");
		Thread.sleep(random.nextInt(1000));
	}
	
	public String toString() {
		return name;
	}

}
