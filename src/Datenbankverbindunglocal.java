
/**
 * @author Fridtjof
 * Schreibt Werte in die lokale Datenbank. Wird im Hauptteil aufgerufen.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


import java.sql.*;
public class Datenbankverbindunglocal implements Runnable  {
	private static final int wartezeit = 1000; //Wartezeit in  Millisekunden
	public void run(){
		
	
		COMReader creader = new COMReader();
		Datenbankverbindung dbverbn = new Datenbankverbindung(); 
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


public class Datenbankverbindung  {
	/**
	 * Die Daten, die für die Verbindung benötigt werden. 
	 * @author Fridtjof
	 *
	 */
	public final class DatabaseConstants
	{
	  private DatabaseConstants() { /* Empty */ }
	  public static final String DRIVER   = "com.mysql.jdbc.Driver";
	  public static final String PROTOCOL = "jdbc:mysql://localhost/";
	  public static final String DATABASE = "semesterprojekt";
	  public static final String URL      = PROTOCOL + DATABASE;
	  public static final String USER     = "root";
	  public static final String PASS     = "";
	}
	// baut Datebankverbindung immer auf und ab
	public void addvalue(double value){
		try{
			Class.forName(DatabaseConstants.DRIVER).newInstance();
		
			Connection con = DriverManager.getConnection(
					DatabaseConstants.URL,DatabaseConstants.USER,DatabaseConstants.PASS);
				
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO messwert VALUES ("+ System.currentTimeMillis()/1000 + ","+value + ")";
			stmt.executeUpdate(sql);
			
		/*
          	// Gibt die gesamte Tabelle aus	
			ResultSet rs = stmt.executeQuery("SELECT * FROM messwert");
			while(rs.next()){
				System.out.println(rs.getInt(1)+ " "+ rs.getString(2));
			}
			
			
			rs.close();
			*/
			stmt.close();
			con.close();

		}catch(Exception e){
			System.out.println("Fehlermeldung: Verbindung fehlgeschlagen :(");
			
		}
		
		
	}

}
}