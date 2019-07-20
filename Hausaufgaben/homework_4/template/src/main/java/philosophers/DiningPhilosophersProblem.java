package philosophers;

/**
 * This is an implementation of the dining philosophers problem.
 * The given number of philosophers is created and started as threads.
 * @author prog2-team
 *
 */
public class DiningPhilosophersProblem {

	final int NUMBER_OF_PHILOSOPHERS = 5;
	final String[] PHILOSOPHER_NAMES = {"Galilei", "Erasmus", "Dionysios", "Dewey", "Demokrit", "Cicero", "Bacon", "Aristoteles"};
	Chopstick[] chopsticks;
	Philosopher[] philosophers;
	
	public DiningPhilosophersProblem() {
		// prepare chopsticks
		chopsticks = new Chopstick[NUMBER_OF_PHILOSOPHERS];
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++){
			chopsticks[i] = new Chopstick();
		}
		// prepare philosophers
		philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++){
			String name;
			if (i < PHILOSOPHER_NAMES.length)
				name = PHILOSOPHER_NAMES[i] + "-" + i;
			else
				name = "Philosopher-i";
			philosophers[i] = new Philosopher(name, chopsticks[i], chopsticks[(i+1) % NUMBER_OF_PHILOSOPHERS]);
		}
		// start philosophers
		for (Philosopher phil: philosophers) {
			new Thread(phil).start();
		}
	}
	
	public static void main(String[] args) {
		new DiningPhilosophersProblem();
	}

}
