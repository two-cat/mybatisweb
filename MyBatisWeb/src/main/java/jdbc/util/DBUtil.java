package jdbc.util;
import java.sql.*;

public class DBUtil {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static String user="c##yl", pwd="oracle";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//-------------------
	
	public static Connection getCon() throws SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}//--------------------
	
}///////////////////////////
