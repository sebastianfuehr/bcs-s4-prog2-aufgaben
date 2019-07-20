package buergeramt;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Represents an Office with a limited number of workplaces for handling a {@link Kunde} 
 * @author prog2-team
 *
 */
public class Amt {

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
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(arbeitsplaetze);
		for (int i=0; i<40; i++) {
			Random random = new Random();
			Anliegen anliegen = Anliegen.class.getEnumConstants()[random.nextInt(2)];
			threadPoolExecutor.execute(new Kunde(i, anliegen));
		}
        try {
            // Thread.sleep(8000);
			threadPoolExecutor.awaitTermination(8, TimeUnit.SECONDS); // bessere Lösung
            threadPoolExecutor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!threadPoolExecutor.isShutdown()) {
			System.out.println("Shutting down...");
        }
        System.out.println("Office shut down and has completed "+threadPoolExecutor.getCompletedTaskCount()+" tasks.");
	}
}
