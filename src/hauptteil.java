/**
 * 
 */

/**Startet die einzelnen Threads.
 * @author Fridtjof
 *
 */
public class hauptteil {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Thread t1 = new Thread (new Datenbanklocal()); // neue Werte
		t1.start();
		Thread t2 = new Thread (new Datenbankabgleich());
		t2.start();
		

	}

}
