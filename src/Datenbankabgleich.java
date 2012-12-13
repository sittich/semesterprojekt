/**
 * 
 */

/**
 * @author Fridtjof
 *
 */
public class Datenbankabgleich implements Runnable{

	public void run() {
		while(true){
			System.out.println("Datenbankabgleich");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
	}

}
}