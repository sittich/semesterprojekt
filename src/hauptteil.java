/**
 * 
 */

/**Startet die einzelnen Komponenten und fügt in einer Endlosschleife Werte in die Datenbank ein. Wartet 1 Sekunde.
 * @author Fridtjof
 *
 */
public class hauptteil {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Thread t1 = new Thread (new Datenbankverbindunglocal());
		t1.start();
		Thread t2 = new Thread (new Datenbankabgleich());
		t2.start();


	}

}
