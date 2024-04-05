// Purpose: This class is used to connect to the database. It uses the JDBC driver to connect to the database. The database name, user and password are hardcoded in this class. The getConnection() method is used to establish the connection to the database and return the connection object.

package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public Connection databaseLink;
	
	public Connection getConnection()
	{
		String databaseName = "librarymanager";
		String databaseUser= "root";
		String databasePassword="Dhanwinder+kpu1";
		
		//String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url,databaseUser, databasePassword);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return databaseLink;
		
		
	}
	
	

}
