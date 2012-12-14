
/**
 * @author Fridtjof
 * Schreibt Werte in die lokale Datenbank. Wird im Hauptteil aufgerufen.
 */

/**
 * Unter buildpath -> configure build path -> libaries muss der mysql-connecter-java-5.1.22-bin.jar hinzugefügt werden 
 * http://www.mysql.de/downloads/connector/j/
 */

/**
 * Ich hab mit xampp einfach einen lokalen sql"server" aufgemacht.
 * Die Datenbank "semesterproejkt" hab ich von Hand angelegt ( CREATE DATABASE semesterprojekt;) USE semesterprojekt;
 * Das Schema messwert habe ich ebenfalls von Hand angelegt ( CREATE TABLE messwert(zeit INTEGER, wert DOUBLE);) 
 * Zum Überprüfen(SHOW TABLES; DESCRIBE messwert;)
 * 
 * @author Fridtjof
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class Datenbanklocal implements Runnable  {
	private static final int wartezeit = 1000; //Wartezeit in  Millisekunden
	public void run(){
		
	
		COMReader creader = new COMReader();
		einfueger dbverbn = new einfueger(); 
		while(true){
		dbverbn.addvalue(creader.getLastValue());
		System.out.println("Datenbankverbindung");
		try {
			Thread.sleep(wartezeit);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	
	
	}





public class einfueger  {
	// baut Datebankverbindung immer auf und ab
	public void addvalue(double value){
		try{
			Class.forName(Datenbankverbindung.DBkonstantenLokal.DRIVER).newInstance();
		
			Connection con = DriverManager.getConnection(
					Datenbankverbindung.DBkonstantenLokal.URL,Datenbankverbindung.DBkonstantenLokal.USER,
					Datenbankverbindung.DBkonstantenLokal.PASS);
				
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO messwert VALUES ("+ System.currentTimeMillis()/1000 + ","+value + ")";
			stmt.executeUpdate(sql);
			
		
          	// Gibt die gesamte Tabelle aus	
			ResultSet rs = stmt.executeQuery("SELECT * FROM messwert");
			while(rs.next()){
				System.out.println(rs.getInt(1)+ " "+ rs.getString(2));
			}
			
			
			rs.close();
			
			stmt.close();
			con.close();

		}catch(Exception e){
			System.out.println("Fehlermeldung: Verbindung fehlgeschlagen :(");
			
		}
		
		
	}

}
}