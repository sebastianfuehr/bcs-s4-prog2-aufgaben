package buergeramt;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Represents an Office with a limited number of workplaces for handling a {@link Kunde} 
 * @author prog2-team
 *
 */
public class Amt {

	static private int kunden = 40;

	/**
	 * Represents the number of workplaces for handling a {@link Kunde} 
	 */
	static private int arbeitsplaetze = 5;
	
	/**
	 * Possible concerns that can be handled by the {@link Amt}
	 * @author prog2-team
	 *
	 */
	enum Anliegen {
		Ummeldung, Fuehrerschein, Personalausweis
	}

	/**
	 * Creates new workplaces for handling a {@link Amt} with some workplaces and starts the work. 
	 */
	public static void main(String[] args) throws InterruptedException{
		ExecutorService executorService = Executors.newFixedThreadPool(arbeitsplaetze);

		Anliegen[] anliegen = Anliegen.values();
		Random ran = new Random();
		for (int i=0; i<kunden; i++) {
			executorService.execute(new Kunde(i, anliegen[ran.nextInt(anliegen.length)]));
		}

		try {
			// Lieber ExecutorService.awaitTermination(...) als Thread.sleep(), weil die Threads im Thread-Pool
			// ja auch früher abgearbeitet sein könnten. In so einem Fall soll das Programm natürlich direkt
			// fortfahren.
			if (!executorService.awaitTermination(8000, TimeUnit.MILLISECONDS)) {
				executorService.shutdown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!executorService.isTerminated()) executorService.shutdownNow();
		}
		System.out.println("Bürgeramt wurde erfolgreich geschlossen.");
	}
}
