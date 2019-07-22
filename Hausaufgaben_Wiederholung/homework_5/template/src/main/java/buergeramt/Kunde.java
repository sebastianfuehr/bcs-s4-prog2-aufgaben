package buergeramt;

import java.util.Random;

/**
 * Represents a citizen, who wants to initiate a {@link Amt.Anliegen} at an {@link Amt}
 * @author prog2-team
 *
 */
public class Kunde implements Runnable {
	
	/**
	 * Some kind of customer ID to distinguish the customers.
	 */
	private int kundennummer;
	
	/**
	 * The concern of that citizen.
	 */
	private Amt.Anliegen anliegen;
	
	/**
	 * @param i The value to be set as ID.
	 * @param anliegen concern that should be handled by the {@link Amt}
	 */
	public Kunde(int i, Amt.Anliegen anliegen){
		this.kundennummer = i;
		this.anliegen = anliegen;
	}

	@Override
	public void run() {
		try {
			Random random = new Random();
			System.out.println("\t" + this + ": Wird aufgerufen. Bitte an Platz " + Thread.currentThread().getName() + ". Antrag fuer " + anliegen + " wird bearbeitet.");
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			System.out.println("\t\t" + this + ": Bitte kommen Sie morgen wieder, ihr Anliegen kann gerade nicht bearbeitet werden.");
		}
		System.out.println("\t\t" + this + ": Antrag fuer " + anliegen + " bearbeitet. DER NAECHSTE BITTE!");

	}

	@Override
	public String toString(){
		return "KUNDE " + kundennummer;
	}
}
