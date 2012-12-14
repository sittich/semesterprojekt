import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



/**
 * @author Fridtjof
 * Verbindet sich mit den beiden Datenbanken und checkt, ob es in der lokalen Datenbank neue Werte gibt. 
 * Wenn ja werden die Werte auch in die globale DB geschrieben.
 * 
 * 
 * 1. Verbindung zu beiden DB aufbauen 
 * 2. neuste Werte aus beiden Db holen
 * 3. globale Db aktualiesiren, falls neue Werte vorhanden
 */
public class Datenbankabgleich implements Runnable{
	/* Ermittelt die größte Zeit in der lokalen Datenbank.
	 * 
	 */
	public static int zeitmaxlokal (){
		try{
			Class.forName(Datenbankverbindung.DBkonstantenGlobal.DRIVER).newInstance();
		
			Connection con = DriverManager.getConnection(
					Datenbankverbindung.DBkonstantenLokal.URL,
					Datenbankverbindung.DBkonstantenLokal.USER,
					Datenbankverbindung.DBkonstantenLokal.PASS);
			
			Statement stmt = con.createStatement();
		
					
          	// Muss das Maximum bekommen
			ResultSet rs = stmt.executeQuery("SELECT max(zeit) FROM messwert");
			rs.next();
			int zeitlokal =rs.getInt(1);
			System.out.println("Maximum lokal:" +zeitlokal);
		
			
			rs.close();
			stmt.close();
			con.close();
			
			return zeitlokal;
			
		}catch(Exception e){
			System.out.println("Fehlermeldung: Verbindung fehlgeschlagen :(");
			
		}
		return 0;
		
	}

	/*
	 * Ermittel die größte Zeit in der globalen Datenbank.
	 */
	public static int zeitmaxglobal (){
		try{
			Class.forName(Datenbankverbindung.DBkonstantenGlobal.DRIVER).newInstance();
		
			Connection con = DriverManager.getConnection(
					Datenbankverbindung.DBkonstantenGlobal.URL,
					Datenbankverbindung.DBkonstantenGlobal.USER,
					Datenbankverbindung.DBkonstantenGlobal.PASS);
			
			Statement stmt = con.createStatement();
		
			
		
          	// Muss das MAximum bekommen
			ResultSet rs = stmt.executeQuery("SELECT max(zeit) FROM messwert");
			rs.next();
			int zeitlokal =rs.getInt(1);
			System.out.println("Maximum global:" +zeitlokal);
		
			
			rs.close();
			stmt.close();
			con.close();
			
			return zeitlokal;
			
		}catch(Exception e){
			System.out.println("Fehlermeldung: Verbindung fehlgeschlagen :(");
			
		}
		return 0;
		
	}
	
	/*
	 * Vergleicht, ob es neue Werte in der lokalen Datenbank gibt, als in der globalen Db. 
	 * Falls ja werden die Werte der lokalen Db in die globale Db gespeichert.
	 */
	public static void einfuegen(){
		
		int maxglobal = zeitmaxglobal();
		int maxlokal = zeitmaxlokal();
		if (maxlokal > maxglobal){
			System.out.println("Neuere Werte vorhanden");
			
			try{
				Class.forName(Datenbankverbindung.DBkonstantenLokal.DRIVER).newInstance();
			
				Connection con = DriverManager.getConnection(
						Datenbankverbindung.DBkonstantenLokal.URL,
						Datenbankverbindung.DBkonstantenLokal.USER,
						Datenbankverbindung.DBkonstantenLokal.PASS);
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM messwert WHERE zeit >"+ maxglobal);
				
				// Verbindung mit der global DB
				Class.forName(Datenbankverbindung.DBkonstantenGlobal.DRIVER).newInstance();
				
				Connection con2 = DriverManager.getConnection(
						Datenbankverbindung.DBkonstantenGlobal.URL,
						Datenbankverbindung.DBkonstantenGlobal.USER,
						Datenbankverbindung.DBkonstantenGlobal.PASS);
				
				rs.next();
				Statement stmt2 = con.createStatement();
			
				
				
				
				
				
				while(rs.next()){
					String sql = "INSERT INTO `semesterprojekt2`.`messwert`(`zeit`, `wert`) VALUES ("+ rs.getInt(1) + ", " + rs.getDouble(2) + ")";
					System.out.println("Sql:" + sql);
					stmt2.executeUpdate(sql);
					
					System.out.println("Neue Werte: " + rs.getInt(1) +" "+ rs.getString(2));
					
				}
			
				
				rs.close();
				stmt2.close();
				stmt.close();
				con2.close();
				con.close();				
			}catch(Exception e){
				System.out.println("Fehlermeldung: Verbindung fehlgeschlagen :(");
				
			}			
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * 
	 * Ruft in einer Endlosschleife die Methode zum einfuegen auf.
	 */
	public void run() {
	
		
		while(true){
			einfuegen();
			System.out.println("Datenbankabgleich");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
	}

}
}