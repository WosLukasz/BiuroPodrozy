package biuro.databaseConnection;

import java.sql.*;


import com.sun.xml.internal.fastinfoset.sax.Properties;

public class GetConnection {
	
	
	
	public static Connection dajPolaczenie() throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
	    
	
	    String dbURL = "jdbc:oracle:thin:@oraas:1521:ora2017";
	    String user = "G1_wolukasz";
	    String pw = "NALEZY PODAC HASLO";
	
	    try{
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection(dbURL,user,pw);
	        System.out.println("zrobiono polaczenie");
	
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    
		return conn;
	
	}
}