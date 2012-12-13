/**
 * 
 */

/**
 * @author Fridtjof
 *
 */
public class Datenbankverbindungglobal {
	
	public final class DatabaseConstants
	{
	  private DatabaseConstants() { /* Empty */ }
	  public static final String DRIVER   = "com.mysql.jdbc.Driver";
	  public static final String PROTOCOL = "jdbc:mysql://localhost/";
	  public static final String DATABASE = "semesterprojekt2";
	  public static final String URL      = PROTOCOL + DATABASE;
	  public static final String USER     = "root";
	  public static final String PASS     = "";
	}
	

}
