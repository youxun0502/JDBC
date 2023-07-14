package tw.ispan.jdbc.util;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public static Connection createSQLServerConnection() {
		Properties props = new Properties();
		
		FileInputStream fis = null;
		Connection conn= null;
		
		try {
			fis = new FileInputStream("src/db.properties");	
			props.load(fis);
			conn = DriverManager.getConnection(props.getProperty("MSSQL_DB_URL"),props.getProperty("MSSQL_DB_USER"),props.getProperty("MSSQL_DB_PASSWORD"));
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return conn;
		
		
		
	}
}

/*

package tw.ispan.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	public static Connection createSQLServerConnection() {
		Properties props = new Properties();
		
		FileInputStream fis = null;
		Connection conn = null;
		
		try {
			fis = new FileInputStream("src/db.properties");
			props.load(fis);
			conn = DriverManager.getConnection(
					props.getProperty("MSSQL_DB_URL"),
					props.getProperty("MSSQL_DB_USER"), 
					props.getProperty("MSSQL_DB_PASSWORD"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}*/