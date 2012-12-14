
/**
 * Die Klasse enthält alle Verbindungsdaten.
 * 
 * 
*/


public class Datenbankverbindung {
	
	public final class DBkonstantenLokal
	{
	  private DBkonstantenLokal() { /* Empty */ }
	  public static final String DRIVER   = "com.mysql.jdbc.Driver";
	  public static final String PROTOCOL = "jdbc:mysql://localhost/";
	  public static final String DATABASE = "semesterprojekt";
	  public static final String URL      = PROTOCOL + DATABASE;
	  public static final String USER     = "root";
	  public static final String PASS     = "";
	}

	//müssen noch angepasst werden
	public final class DBkonstantenGlobal
	{
		public DBkonstantenGlobal(){ /*empty */ }
		public static final String DRIVER   = "com.mysql.jdbc.Driver";
		public static final String PROTOCOL = "jdbc:mysql://localhost/";
		public static final String DATABASE = "semesterprojekt2";
		public static final String URL      = PROTOCOL + DATABASE;
		public static final String USER     = "root";
		public static final String PASS     = "";
		
		
	}
}
