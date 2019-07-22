package philosophers;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a chopstick used by the philosophers.
 * If a philosopher holds the chopstick and another wants to
 * pick it up he has to wait until the chopstick is free.
 * 
 * @author prog2-team
 *
 */
public class Chopstick {
	
	/** flag showing whether the chopstick is in use */
	private boolean inUse;

	public ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Waits until the chopstick is free and picks it up.
	 */
	public void pickUp() { // Here has been the read-modify-write problem, solved in Philosopher class
		setInUse(true);
	}
	
	/**
	 * Puts the chopstick down so i can be used by another philosopher.
	 */
	public void putDown() {
		setInUse(false);
	}
	
	/**
	 * Sets the {@link #inUse} variable.
	 * @param newValue
	 */
	private void setInUse(boolean newValue) {
		inUse = newValue;
	}
	
	/**
	 * @return true, if the chopstick is in use, otherwise false
	 */
	private boolean isInUse() {
		return inUse;
	}

}
